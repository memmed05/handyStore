package com.example.handy.services.servicesImpl;

import com.example.handy.dtos.City;
import com.example.handy.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private static final String URL = "https://en.wikipedia.org/wiki/List_of_cities_in_Azerbaijan";

    @Override
    public List<City> getAllCities() {
        return List.of(
                new City(1, "Baku"),
                new City(2, "Sheki")
        );

    }
}
