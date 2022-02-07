package com.springboot.mehedizaman.restcountries.entities;

import java.util.List;

public class Countries {
    private String name;
    private String capital;
    private String region;
    private String subregion;
    private long population;
    private double area;
    private List<String> borders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    @Override
    public String toString() {
        return "CountriesBO [name=" + name + ", capital=" + capital + ", region=" + region + ", subregion=" + subregion
                + ", population=" + population + ", area=" + area + ", borders=" + borders + "]";
    }
}
