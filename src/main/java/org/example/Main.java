package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Main {

    private static Long time0;
    private static Long timer0;
    private static Long time;
    private static Long timer1;
    private static Long timer2;
    private static Long timer3;

    public static void main(String[] args) throws IOException {
        time0 = startTime();
        System.out.println("Старт программы!");

        String fileName = args[0];//"lng.txt";//
        File inputFile = new File(fileName);

        if(!inputFile.exists()) {
            System.out.println("Файл " + inputFile.getName() + " не найден!");
        }
        else {
            time = startTime();
            LinesReport lineReport = FileParser.parsing(inputFile);
            timer1 = endTime(time);
            System.out.println("Время выполнения разборки файла: " + timer1 + " мс");

            time = startTime();
            List<ColumnValues> groups = Grouper.grouping(lineReport.getLines());
            timer2 = endTime(time);
            System.out.println("\nВремя выполнения группировки: " + timer2 + " мс");

            time = startTime();
            int groupNumber = FileGroups.create(groups, lineReport.getLines());

            timer3 = endTime(time);
            System.out.println("\nВремя формирования выходного файла: " + timer3 + " мс");

            timer0 = endTime(time0);
            System.out.println("\nВремя выполнения программы: " + timer0);


            // Файл отчёт
            FileWriter fileWriter = new FileWriter("report.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("------ Разборка файла ------");
            printWriter.printf("В файле обработано %d строк(а/и), из них:\n", lineReport.getLines().size()+lineReport.getBadLines());
            printWriter.println("   - пригодных: " + lineReport.getLines().size() + ";");
            printWriter.println("   - негодных:  " + lineReport.getBadLines() + ".");
            printWriter.printf("Время выполнения разборки файла: %d мс\n", timer1);
            printWriter.println("------- Группировка -------");
            printWriter.println("Групп найдено: " + groupNumber + " шт.");
            printWriter.println("Время выполнения группировки: " + timer2 + " мс");
            printWriter.println("------- Формирование файла групп -------");
            printWriter.println("Время формирования выходного файла: " + timer3 + " мс");
            printWriter.printf("\nВремя выполнения всей программы: " + timer0);
            printWriter.close();
        }

    }

    public static Long startTime() { return System.currentTimeMillis(); }
    public static long endTime(Long startTime) {return System.currentTimeMillis() - startTime; }
}

