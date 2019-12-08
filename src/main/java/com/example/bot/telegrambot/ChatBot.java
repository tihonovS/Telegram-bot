package com.example.bot.telegrambot;

import com.example.bot.model.City;
import com.example.bot.sesvice.CityInformationService;
import com.example.bot.sesvice.CityService;
import com.example.bot.util.StringJoiningUtils;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:telegrambot.properties")
@BotController
public class ChatBot implements TelegramMvcController {

    private final CityService cityService;

    @Value("${telegram.bot.name}")
    private String botName;

    @Value("${telegram.bot.token}")
    private String botToken;

    public ChatBot(CityService cityService) {
        this.cityService = cityService;
    }

    @BotRequest(value = "/start")
    public BaseRequest getStart(Chat chat) {
        return new SendMessage(chat.id(), "Hi this is a test case for On Travel Solutions, " +
                "to enter a message, use /command + message, " +
                "for example \"/getCityInformation  message\"\"");
    }

    @BotRequest(value = "/help")
    public BaseRequest help(Chat chat) {
        return new SendMessage(chat.id(), "/getListCities -- list of cities " +
                "/getCityInformation + cityName -- Get information about the city " +
                "/addCity + cityName -- add a new city " +
                "/addCityInformation + cityName + discription -- add a description to the city " +
                "/deleteCity + cityName -- remove the city and its description " +
                "/editCity + cityName + newCityName -- edit the name of the city " +
                "/editCityInformation + cityName + newDiscription  -- edit the description of the city ");
    }

    @BotRequest(value = "/getListCities")
    public BaseRequest getListCities(Chat chat) {
        return new SendMessage(chat.id(), cityService.getCities());
    }

    @MessageRequest(value = "/getCityInformation {message:[А-я]+}")
    public BaseRequest getCityInformation(Chat chat, @BotPathVariable("message") String message) {
        return new SendMessage(chat.id(), cityService.getInformationCitiesByCityTitle(message));
    }

    @MessageRequest(value = "/addCity {message:[\\s\\S]+}")
    public BaseRequest addCity(Chat chat,  @BotPathVariable("message") String message){
        cityService.saveCity(message);
        return new SendMessage(chat.id(), "Done");
    }

    @MessageRequest(value = "/addCityInformation {nameCity:[А-я]+} {discription:[\\s\\S]+}")
    public BaseRequest addCityInformation(Chat chat,  @BotPathVariable("nameCity") String nameCity,  @BotPathVariable("discription") String discription){
        City city = cityService.saveCityInformation(nameCity, discription);
        if (city != null)
            return new SendMessage(chat.id(), "Done");
        else
            return new SendMessage(chat.id(), "Сity not found");
    }
    @MessageRequest(value = "/deleteCity {message:[\\s\\S]+}")
    public BaseRequest deleteCity(Chat chat,  @BotPathVariable("message") String message){
        cityService.deleteCity(message);
        return new SendMessage(chat.id(), "Done");
    }

    @MessageRequest(value = "/editCity {nameCity:[А-я]+} {newNameCity:[\\s\\S]+}")
    public BaseRequest editCity(Chat chat,  @BotPathVariable("nameCity") String nameCity,  @BotPathVariable("newNameCity") String newNameCity){
        City city = cityService.editCity(nameCity, newNameCity);
        if (city != null)
            return new SendMessage(chat.id(), "Done");
        else
            return new SendMessage(chat.id(), "Сity not found");
    }

    @MessageRequest(value = "/editCityInformation {nameCity:[А-я]+} {newDiscription:[\\s\\S]+}")
    public BaseRequest editCityInformation(Chat chat,  @BotPathVariable("nameCity") String nameCity,  @BotPathVariable("newDiscription") String newDiscription){
        City city = cityService.editCityInformation(nameCity, newDiscription);
        if (city != null)
            return new SendMessage(chat.id(), "Done");
        else
            return new SendMessage(chat.id(), "Сity not found");
    }

    public String getToken() {
        return botToken;
    }

    public String getBotName() {
        return botName;
    }
}
