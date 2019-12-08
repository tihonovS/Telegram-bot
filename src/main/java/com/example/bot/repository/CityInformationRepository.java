package com.example.bot.repository;

import com.example.bot.model.City;
import com.example.bot.model.CityInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityInformationRepository extends JpaRepository<CityInformation, Long> {
    List<CityInformation> findByCity(City city);
}
