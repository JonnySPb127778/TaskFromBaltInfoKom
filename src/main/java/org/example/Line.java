package org.example;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private List<Long> series; // коллекция из полей строки
    private String tempString; // локальная переменная класса

    public Line() {
        this.series = new ArrayList<>();
    }

    public List<Long> getSeries() {
        return series;
    }


    public boolean add (String field) {
        if(check(field)) { // если поле корректное, то
            // если длинна поля != 0, то добавляем в коллекцию число полученное
            // из преобразования последовательности десятичных цифр
            if(tempString.length() != 0) series.add(Long.parseLong(tempString));
            else series.add(null); // иначе добавляем в коллекцию строк null
            return true;
        }
        return false;
    }

    // проверка поля на корректность
    private boolean check(String field) {
        if( field.length() < 2                      // если длинна строки менее 2-х символов
         || field.charAt(0) != '"'                  // или первый символ в поле не "
         || field.charAt(field.length()-1) != '"'   // или последний символ в поле не "
        ) return false;                             // то поле не корректное!

        tempString = field.substring(1,field.length()-1); // убираем первый и последний символы из поля (символы ")
        // Если длинна поля 0 или поле состоит из одних десятичных цифр, то поле корректное!
        if(tempString.length() == 0 || tempString.matches("\\d+")) return true;
        return false; // иначе поле не корректное!
    }
}

