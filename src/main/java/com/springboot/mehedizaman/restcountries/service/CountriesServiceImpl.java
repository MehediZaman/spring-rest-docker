package com.springboot.mehedizaman.restcountries.service;

import com.springboot.mehedizaman.restcountries.entities.Countries;
import com.springboot.mehedizaman.restcountries.utility.CountryListObjMap;
import com.springboot.mehedizaman.restcountries.utility.SortJsonDataByArea;
import com.springboot.mehedizaman.restcountries.utility.UrlConnection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountriesServiceImpl implements CountriesService {

    private static final String RestCountriesURL = "https://restcountries.com/v2";

    @Override
    @Cacheable(value = "allCountriesDetails")
    public List<Countries> getAllCountiresData() {
        return CountryListObjMap.getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/all")));
    }

    @Override
    @Cacheable(value = "tenBiggestCountries")
    public List<Countries> getTenBiggestCountriesDetails() {
        List<Countries> list = null;
        list = CountryListObjMap.getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/all")));
        return SortJsonDataByArea.sortingJsonData(list);
    }

    @Override
    @Cacheable(value = "TenBiggestRegionCountries", key = "#regionName")
    public List<Countries> getTenBiggestRegionCountriesDetails(String regionName) {
        List<Countries> list = null;
        list = CountryListObjMap
                .getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/region/" + regionName)));
        return SortJsonDataByArea.sortingJsonData(list);
    }

    @Override
    @Cacheable(value = "TenRegionalBlocCountries", key = "#subRegionName")
    public List<Countries> getSubRegionCountriesDetails(String subRegionName) {
        List<Countries> list = null;
        List<Countries> sortedList = new ArrayList<Countries>();
        list = CountryListObjMap
                .getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/regionalbloc/" + subRegionName)));
        for (Countries li : list) {
            if (li.getBorders().size() > 3) {
                sortedList.add(li);
            }
        }
        return sortedList;
    }

    @Override
    public String getPopulationSubRegionCountries(String subRegionName) {
        long totalPopulation = 0;
        List<Countries> list = CountryListObjMap
                .getMappedObj(UrlConnection.getConnection(RestCountriesURL.concat("/regionalbloc/" + subRegionName)));
        for (Countries countries : list) {
            totalPopulation = (totalPopulation + countries.getPopulation());
        }
        return "Total Population of subregion \"" + subRegionName + "\" is " + totalPopulation;
    }
}
