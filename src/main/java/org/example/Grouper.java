package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grouper {
    public static List<ColumnValues> grouping(List<Line> lines) {
        // Создание сортированных коллекций из уникальных значений по столбцам
        List<ColumnValues> columnValuesList = new ArrayList<>();
        List<Long> series;  // Коллекция из элементов одной строки
        Long value;         // значение одного элемента

        // Перебор строк из коллекции строк
        for (int i = 0; i < lines.size(); i++) { // i - index строки в коллекции строк
            series = lines.get(i).getSeries(); // Загрузка строки из коллекции со строками

            // До создание коллекций столбцов, если в строке больше столбцов
            int deltaSize = series.size() - columnValuesList.size();
            for (int s = 0; s < deltaSize; s++) columnValuesList.add(new ColumnValues());

            // Перебор элементов строки
            for (int c = 0; c < series.size(); c++) {
                value = series.get(c);
                // если не пустой элемент, то добавить в мапу текущего столбца
                // добавить index строки по ключу value
                if(value != null) columnValuesList.get(c).addToColumn(value, i);
            }
        }

        // Перебор получившихся мап столбцов с удалением элементов содержащих единственный index строки
        HashMap<Long, ArrayList<Integer>> column;
        for (int c = 0; c < columnValuesList.size(); c++) {
            column = columnValuesList.get(c).getColumn(); // получить мапу столбца
            column.entrySet().removeIf(entry -> entry.getValue().size() == 1); // удалить элемент с одной записью
        }
        // В результате мы получаем мапы столбцов, каждая из которых содержит уникальные значения исходного столбца
        // имеющие не менее 2-х записей в столбце - т.е. наши искомые ГРУППЫ.

        return columnValuesList; // Вернуть коллекцию с мапами столбцов с группами
    }
}
