package com.example.bot.sesvice;

import com.example.bot.model.City;
import com.example.bot.model.CityInformation;
import com.example.bot.repository.CityInformationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityInformationService {

    private CityInformationRepository cityInformationRepository;

    public CityInformationService(CityInformationRepository cityInformationRepository) {
        this.cityInformationRepository = cityInformationRepository;
    }

    public List<CityInformation> findInformationCityByCity(City city) {
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

    public void editCityInformation(City city, String discription) {
        List<CityInformation> cityInformations = findInformationCityByCity(city);
        CityInformation cityInformation = cityInformations.stream().reduce((first, second) -> second).get();
        cityInformation.setDescription(discription);
        cityInformationRepository.save(cityInformation);
    }

    public void deleteCityInformationByCity(City city) {
        List<CityInformation> informationCities = findInformationCityByCity(city);

        informationCities.forEach(cityInformation -> cityInformationRepository.delete(cityInformation));
    }
}
