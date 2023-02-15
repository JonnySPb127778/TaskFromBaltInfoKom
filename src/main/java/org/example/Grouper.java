package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grouper {
    public static List<ColumnValues> grouping(List<Line> lines) {
        // Создание сортированных коллекций из уникальных значений по столбцам
        List<ColumnValues> columnValuesList = new ArrayList<>();
        List<Long> series;
        Long value;

        for (int i = 0; i < lines.size(); i++) {
            series = lines.get(i).getSeries();

            // Досоздание коллекций столбцов, если в строке больше столбцов
            int deltaSize = series.size() - columnValuesList.size();
            for (int s = 0; s < deltaSize; s++) columnValuesList.add(new ColumnValues());

            //
            for (int c = 0; c < series.size(); c++) {
                value = series.get(c);
                if(value != null) columnValuesList.get(c).addToColumn(value, i);
            }
        }

        HashMap<Long, ArrayList<Integer>> column;
        for (int c = 0; c < columnValuesList.size(); c++) {
            column = columnValuesList.get(c).getColumn();
            column.entrySet().removeIf(entry -> entry.getValue().size() == 1);
        }

        return columnValuesList;
    }
}
