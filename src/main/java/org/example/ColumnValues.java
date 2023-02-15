package org.example;

import java.util.ArrayList;
import java.util.HashMap;


public class ColumnValues {
    private HashMap<Long, ArrayList<Integer>> column;

    public ColumnValues() {
        this.column = new HashMap<>();
    }

    public HashMap<Long, ArrayList<Integer>> getColumn() {
        return column;
    }

    public void addToColumn (Long value, Integer lineIndex) {
        ArrayList<Integer> indexLineList = column.get(value);
        if(indexLineList == null) {
            indexLineList = new ArrayList<>();
            indexLineList.add(lineIndex);
            column.put(value, indexLineList);
        }
        else indexLineList.add(lineIndex);
    }
}
