package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileGroups {
    public static int create(List<ColumnValues> groups, List<Line> lines) throws IOException {

        FileWriter fileWriter = new FileWriter("groups.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int groupNumber = 0;

        for(int g=0; g < groups.size(); g++) {
            for (HashMap.Entry<Long, ArrayList<Integer>> column : groups.get(g).getColumn().entrySet()) {

                printWriter.printf("\nГруппа %d\n", ++groupNumber);

                ArrayList<Integer> lineIndexArray = column.getValue();
                for (Integer index : lineIndexArray) {
                    List<Long> series = lines.get(index).getSeries();

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

        System.out.println("Групп найдено: " + groupNumber + " шт.");
        return  groupNumber;
    }
}
