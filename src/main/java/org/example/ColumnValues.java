package org.example;

import java.util.ArrayList;
import java.util.HashMap;


public class ColumnValues {
    // В данной мапе в качестве ключа выступает значение в поле строки (value),
    // а в качестве данных выступает коллекция из индексов строк по коллекции строк в которых
    // в соответствующем столбце присутствует значение value
    private HashMap<Long, ArrayList<Integer>> column;

    public ColumnValues() {
        this.column = new HashMap<>();
    }

    public HashMap<Long, ArrayList<Integer>> getColumn() {
        return column;
    }

    public void addToColumn (Long value, Integer lineIndex) {
        // попытка получить список индексов по ключу value
        ArrayList<Integer> indexLineList = column.get(value);
        if(indexLineList == null) { // если такого поля в мапе нет, то создаём его
            indexLineList = new ArrayList<>();
            indexLineList.add(lineIndex);
            column.put(value, indexLineList);
        } // если в мапе такое поле есть, то добавляем в коллекцию индексов индекс текущей строки
        else indexLineList.add(lineIndex);
    }
}

