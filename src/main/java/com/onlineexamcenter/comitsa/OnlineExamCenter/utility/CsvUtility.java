package com.onlineexamcenter.comitsa.OnlineExamCenter.utility;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Subjects;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CsvUtility {
    public <T> void writeCsvData(HttpServletResponse response, List<T> objects){

        if (objects.isEmpty()) {
            System.out.println("No data to export!");
            return; // No data to export
        }


        Class<?> clazz = objects.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();
        String fileName =objects.get(0).getClass().getSimpleName();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".csv\"");

        try {
            //CSVWriter writer = new CSVWriter(new FileWriter(fileName));
            CSVWriter writer = new CSVWriter(response.getWriter());
            writer.writeAll(Collections.singletonList(getHeaders(fields)));
            List<String[]> dataRows = objects.stream()
                    .map(object -> extractFieldValues(object, fields))
                    .collect(Collectors.toList());
            System.out.println("data is ");
            dataRows.stream().forEach(data1-> System.out.println("data1 "+data1));
            writer.writeAll(dataRows);
        } catch (IOException e) {
            System.out.println("failed while writing data "+e);
            throw new RuntimeException(e);
        }
    }
    private <T> String[] extractFieldValues(T object, Field[] fields) {
        System.out.println("inside the extract value function");
        return Stream.of(fields)
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(object);
                        System.out.println("field value"+ value);
                        return (value != null) ? value.toString() : "";
                    } catch (IllegalAccessException e) {
                        // Handle IllegalAccessException
                        return "failed to get the data";
                    }
                })
                .toArray(String[]::new);
    }
    private String[] getHeaders(Field[] fields){
        String [] headers = new String[fields.length];
        for (int i=0;i< fields.length;i++){
            headers[i] = fields[i].getName();
        }
        return headers;
    }
}
