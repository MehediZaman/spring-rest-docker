package com.springboot.mehedizaman.restcountries.controller;

import com.springboot.mehedizaman.restcountries.entities.Countries;
import com.springboot.mehedizaman.restcountries.service.CountriesService;
import com.springboot.mehedizaman.restcountries.utility.CreateCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This is controller. Handle all the request.
 */
@RestController
public class CountriesController {
    @Autowired
    CountriesService service;

    /**
     * This request shows a list of all countries in the world
     *
     * @param responseType - responseType as optional parameter to change response
     *                     Type. Default value "" its return JSON response
     * @return JSON/CSV - Based on responseType parameter
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Countries> getAllCountiresDetails(
            @RequestParam(required = false, defaultValue = "") String responseType, HttpServletResponse response) {
        List<Countries> list;
        if (responseType.equals("csv")) {
            CreateCsv.createCsv("All countries the in the world", response, service.getAllCountiresData());
            list = null;
        } else {
            list = service.getAllCountiresData();
        }
        return list;
    }

    /**
     * This request shows 10 biggest regions in the world
     *
     * @param responseType - responseType as optional parameter to change response
     *                     Type. Default value "" its return JSON response
     * @return JSON/CSV - Based on responseType parameter
     */
    @RequestMapping(value = "/tenbiggest/region", method = RequestMethod.GET, produces = "application/json")
    public List<Countries> getTenBiggestRegion(@RequestParam(required = false, defaultValue = "") String responseType,
                                                 HttpServletResponse response) {
        List<Countries> list;
        if (responseType.equals("csv")) {
            CreateCsv.createCsv("Ten biggest region in the world", response, service.getTenBiggestCountriesDetails());
            list = null;
        } else {
            list = service.getTenBiggestCountriesDetails();
        }
        return list;
    }

    /**
     * This request shows 10 biggest countries of determined region
     *
     * @param regionName   - path variable get details based on this parameter
     * @param responseType - responseType as optional parameter to change response
     *                     Type. Default value "" its return JSON response
     * @return JSON/CSV - Based on responseType parameter
     */
    @RequestMapping(value = "/tenbiggest/regionCountries/{regionName}", produces = "application/json")
    public List<Countries> getTenBiggestCountiresInRegion(@PathVariable("regionName") String regionName,
                                                            @RequestParam(required = false, defaultValue = "") String responseType, HttpServletResponse response) {
        List<Countries> list;
        if (responseType.equals("csv")) {
            CreateCsv.createCsv("10 Biggest Countries of " + regionName, response,
                    service.getTenBiggestRegionCountriesDetails(regionName));
            list = null;
        } else {
            list = service.getTenBiggestRegionCountriesDetails(regionName);
        }
        return list;
    }

    /**
     * This request shows a list of all countries of determined sub-region
     *
     * @param subRegionName - path variable get details based on this parameter
     * @param responseType  - responseType as optional parameter to change response
     *                      Type. Default value "" its return JSON response
     * @return JSON/CSV - Based on responseType parameter
     */
    @RequestMapping(value = "/subregion/{subRegionName}", produces = "application/json")
    public List<Countries> getCountiresInSubRegion(@PathVariable("subRegionName") String subRegionName,
                                                   @RequestParam(required = false, defaultValue = "") String responseType, HttpServletResponse response) {
        List<Countries> list;
        if (responseType.equals("csv")) {
            CreateCsv.createCsv("All countries determined in subregion " + subRegionName, response,
                    service.getSubRegionCountriesDetails(subRegionName));
            list = null;
        } else {
            list = service.getSubRegionCountriesDetails(subRegionName);
        }
        return list;

    }

    /**
     * This returns total population of sub-region countries
     *
     * @param subRegionName - path variable get details based on this parameter
     * @param responseType  - responseType as optional parameter to change response
     *                      Type. Default value "" its return JSON response
     * @return JSON/CSV - Based on responseType parameter
     */
    @RequestMapping(value = "/subRegionCountriesPopulation/{subRegionName}")
    public String getSubRegionCountriesPopulation(@PathVariable String subRegionName,
                                                  @RequestParam(required = false, defaultValue = "") String responseType, HttpServletResponse response) {
        return service.getPopulationSubRegionCountries(subRegionName);
    }
}
