package com.example.bot.sesvice;

import com.example.bot.model.City;
import com.example.bot.model.CityInformation;
import com.example.bot.repository.CityRepository;
import com.example.bot.util.StringJoiningUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

    private CityInformationService cityInformationService;

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository, CityInformationService cityInformationService) {
        this.cityInformationService = cityInformationService;
        this.cityRepository = cityRepository;
    }

    public String getCities() {
        List<String> cities = cityRepository.findAll().stream()
                .map(City::getTitle).collect(Collectors.toList());

        return StringJoiningUtils.join(cities);
    }

    public String getInformationCitiesByCityTitle(String title) {
        City cityIdFromTitle = getCityFromTitle(title);
        List<CityInformation> cityInformationList = cityInformationService.findInformationCityByCityId(cityIdFromTitle);

        List<String> stringList = cityInformationList.stream().map(CityInformation::getDescription).collect(Collectors.toList());
        return StringJoiningUtils.join(stringList);
    }

    public void saveCity(String message) {
        City city = new City();
        city.setTitle(message);
        cityRepository.save(city);
    }

    public City saveCityInformation(String city, String discription) {
       return cityInformationService.addCityInformation(getCityFromTitle(city), discription);
    }

    public void deleteCity(String title) {
        City city = getCityFromTitle(title);
        cityInformationService.deleteCityInformationByCity(city);
        cityRepository.delete(city);
    }

    public City getCityFromTitle(String title) {
        return cityRepository.findByTitle(title);
    }
}
