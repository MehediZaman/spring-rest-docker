package com.springboot.mehedizaman.restcountries.utility;

import com.springboot.mehedizaman.restcountries.entities.Countries;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateCsv {
    public static void createCsv(String csvName, HttpServletResponse response, List<Countries> list) {
        try {
            response.setContentType("text/csv");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + csvName + ".csv";
            response.setHeader(headerKey, headerValue);

            ICsvBeanWriter writer = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = { "Name", "Capital", "Region", "Subregion", "Population", "Area", "Borders" };
            String[] nameMapping = { "name", "capital", "region", "subregion", "population", "area", "borders" };
            writer.writeHeader(csvHeader);
            for (Countries cbo : list) {
                writer.write(cbo, nameMapping);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
