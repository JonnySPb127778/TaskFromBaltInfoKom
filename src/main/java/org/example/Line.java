package org.example;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private List<Long> series;
    private String tempString;

    public Line() {
        this.series = new ArrayList<>();
    }

    public List<Long> getSeries() {
        return series;
    }


    public boolean add (String field) {
        if(check(field)) {
            if(tempString.length() != 0) series.add(Long.parseLong(tempString));
            else series.add(null);
            return true;
        }

        return false;
    }

    private boolean check(String field) {

        if( field.length() < 2
         || field.charAt(0) != '"'
         || field.charAt(field.length()-1) != '"'
        ) return false;

        tempString = field.substring(1,field.length()-1);

        if(tempString.length() == 0 || tempString.matches("\\d+")) return true;

        return false;
    }
}
