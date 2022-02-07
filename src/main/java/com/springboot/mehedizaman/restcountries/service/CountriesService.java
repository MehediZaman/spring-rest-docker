package com.springboot.mehedizaman.restcountries.service;

import com.springboot.mehedizaman.restcountries.entities.Countries;

import java.util.List;

public interface CountriesService {
    public List<Countries> getAllCountiresData();

    public List<Countries> getTenBiggestCountriesDetails();

    public List<Countries> getTenBiggestRegionCountriesDetails(String regionName);

    public List<Countries> getSubRegionCountriesDetails(String subRegionName);

    public String getPopulationSubRegionCountries(String subRegionName);
}
