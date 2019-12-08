package com.example.bot.sesvice;

import com.example.bot.model.City;
import com.example.bot.model.CityInformation;
import com.example.bot.repository.CityInformationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityInformationService {

    private CityInformationRepository cityInformationRepository;

    public CityInformationService(CityInformationRepository cityInformationRepository) {
        this.cityInformationRepository = cityInformationRepository;
    }

    public List<CityInformation> findInformationCityByCityId(City city) {
        return cityInformationRepository.findByCity(city);
    }

    public City addCityInformation(City city, String discription) {
        if (city == null) {
            CityInformation cityInformation = new CityInformation();
            cityInformation.setCity(city);
            cityInformation.setDescription(discription);
            cityInformationRepository.save(cityInformation);
        }
        return city;
    }

    public void deleteCityInformationByCity(City city) {
        List<CityInformation> informationCities = findInformationCityByCityId(city);

        informationCities.forEach(cityInformation -> cityInformationRepository.delete(cityInformation));
    }
}
