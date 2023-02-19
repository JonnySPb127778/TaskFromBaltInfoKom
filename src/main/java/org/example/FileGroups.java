package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Формирование файла с перечнем получившихся групп и подсчётом их кол-ва
public class FileGroups {
    public static int create(List<ColumnValues> groups, List<Line> lines) throws IOException {
        // Инициализируем писателя в файл
        FileWriter fileWriter = new FileWriter("groups.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int groupNumber = 0;

        // Перебор по списку столбцов
        for(int g=0; g < groups.size(); g++) {
            // Перебор по мапам столбцов с группами
            for (HashMap.Entry<Long, ArrayList<Integer>> column : groups.get(g).getColumn().entrySet()) {

                printWriter.printf("\nГруппа %d\n", ++groupNumber); // заголовок группы

                ArrayList<Integer> lineIndexArray = column.getValue(); // Извлечь коллекцию с index строк
                // Перебор по коллекции индексов строк
                for (Integer index : lineIndexArray) {
                    // получить строку из коллекции строк по индексу строки
                    List<Long> series = lines.get(index).getSeries();

                    // при помощи билдера сформировать строку для вывода в файл
                    StringBuilder lineBuilder = new StringBuilder();

                    for (Long field : series) {
                        String s;
                        if(field == null) s = "\"           \"; ";
                        else s = String.format("\"%11d\"; ", field);
                        lineBuilder.append(s);
                    }
                    lineBuilder.append("\n");
                    String result = lineBuilder.toString();
                    printWriter.printf(result);
                }
            }
        }
        printWriter.close();

        // Вывод в консоль количества найденных групп
        System.out.println("Групп найдено: " + groupNumber + " шт.");
        return  groupNumber;
    }
}

