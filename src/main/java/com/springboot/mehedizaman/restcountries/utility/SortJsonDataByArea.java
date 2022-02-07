package com.springboot.mehedizaman.restcountries.utility;

import com.springboot.mehedizaman.restcountries.entities.Countries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortJsonDataByArea {
    public static List<Countries> sortingJsonData(List<Countries> list) {
        Collections.sort(list, new Comparator<Countries>() {
            @Override
            public int compare(Countries o1, Countries o2) {
                return -Double.valueOf(o1.getArea()).compareTo(Double.valueOf(o2.getArea()));
            }
        });
        List<Countries> sortedlist = new ArrayList<Countries>();
        for (int i = 0; i < 10; i++) {
            if (list.get(i).getArea() != 0 || list.get(i).getArea() != Double.NaN) {
                sortedlist.add(i, list.get(i));
            }
        }
        return sortedlist;
    }
}
