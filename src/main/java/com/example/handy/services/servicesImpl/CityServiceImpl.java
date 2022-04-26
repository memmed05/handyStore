package com.example.handy.services.servicesImpl;

import com.example.handy.dtos.City;
import com.example.handy.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private static final String URL = "https://api.opendata.az/v1/json/map/districts?pretty";

    @Override
    public List<City> getAllCities() {

        return List.of(
                new City(1, "Baku"),
                new City(2, "Sheki")
        );

    }
}
