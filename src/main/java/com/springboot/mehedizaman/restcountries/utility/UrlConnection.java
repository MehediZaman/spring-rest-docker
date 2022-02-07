package com.springboot.mehedizaman.restcountries.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.net.URL;

public class UrlConnection {
    // To retrieve data from the URL which we provide with
    public static String getConnection(String countries_url){
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(countries_url);
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
