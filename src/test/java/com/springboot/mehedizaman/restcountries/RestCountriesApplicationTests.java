package com.springboot.mehedizaman.restcountries;

import com.springboot.mehedizaman.restcountries.entities.Countries;
import com.springboot.mehedizaman.restcountries.service.CountriesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class RestCountriesApplicationTests {

    @Autowired
    CountriesService service;

    /**
     * Unit test for "List the 10 biggest countries of a determined region of the world (Europe, Asia, Oceania, Americas, etc)"
     */
    @ParameterizedTest
    @ValueSource(strings = {"Asia", "Europe", "Americas", "Oceania"})
    void testGetTenBiggestRegionCountryList(String regionName) {
        List<Countries> countries = service.getTenBiggestRegionCountriesDetails(regionName);
        System.out.println(List.of(countries));
        if (Objects.equals(regionName, "Asia")) {
            assert countries.get(0).toString().equals("CountriesBO [name=China, capital=Beijing, region=Asia, subregion=Eastern Asia, population=1402112000, area=9640011.0, borders=[AFG, BTN, MMR, HKG, IND, KAZ, PRK, KGZ, LAO, MAC, MNG, PAK, RUS, TJK, VNM, NPL]]");

        } else if (Objects.equals(regionName, "Europe")) {
            assert countries.get(0).toString().equals("CountriesBO [name=Russian Federation, capital=Moscow, region=Europe, subregion=Eastern Europe, population=144104080, area=1.7124442E7, borders=[AZE, BLR, CHN, EST, FIN, GEO, KAZ, PRK, LVA, LTU, MNG, NOR, POL, UKR]]");
        } else if (Objects.equals(regionName, "Americas")) {
            assert countries.get(0).toString().equals("CountriesBO [name=Canada, capital=Ottawa, region=Americas, subregion=Northern America, population=38005238, area=9984670.0, borders=[USA]]");
        } else assert !Objects.equals(regionName, "Oceania") || countries.get(0).toString().equals("CountriesBO [name=Australia, capital=Canberra, region=Oceania, subregion=Australia and New Zealand, population=25687041, area=7692024.0, borders=null]");
    }

    private String encodePathVariable (String subregionName) throws UnsupportedEncodingException {
        return URLEncoder.encode(subregionName, StandardCharsets.UTF_8.name()).replaceAll("\\+","%20");
    }
    /**
     * Unit test for "List all the countries of a determined subregion (South America, West Europe,  Eastern Asia, etc) that has borders with more than 3 countries."
     */
    @ParameterizedTest
    @ValueSource(strings = {"Western Europe", "Eastern Europe", "North America", "Northern Africa"})
    void testGetSubRegionCountriesDetails(String subregionName) throws UnsupportedEncodingException {
        List<Countries> countries = service.getSubRegionCountriesDetails(encodePathVariable (subregionName));
//        System.out.println(List.of(countries));
//        assert countries.get(0).toString().equals("CountriesBO [name=Colombia, capital=Bogotá, region=Americas, subregion=South America, population=50882884, area=1141748.0, borders=[BRA, ECU, PAN, PER, VEN]]");
    }

    /**
     * Unit test for "List the population of a subregion, including the countries that are part of it."
     */
    @Test
    void testGetPopulationSubRegionCountries() {
        List<Countries> countries = service.getTenBiggestCountriesDetails();
        System.out.println(List.of(countries));
        assert countries.get(0).toString().equals("CountriesBO [name=Russian Federation, capital=Moscow, region=Europe, subregion=Eastern Europe, population=144104080, area=1.7124442E7, borders=[AZE, BLR, CHN, EST, FIN, GEO, KAZ, PRK, LVA, LTU, MNG, NOR, POL, UKR]]");
    }

}
