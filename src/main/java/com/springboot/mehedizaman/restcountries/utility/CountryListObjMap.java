package com.springboot.mehedizaman.restcountries.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mehedizaman.restcountries.entities.Countries;

import java.util.List;

public class CountryListObjMap {
    public static List<Countries> getMappedObj(String ja) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Countries> list = null;
        try {
            list = mapper.readValue(ja, new TypeReference<List<Countries>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }
}
