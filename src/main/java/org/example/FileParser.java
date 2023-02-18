package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Парсер входного файла
public class FileParser {
    public static LinesReport parsing(File inputFile) throws IOException {
        // Коллекция из строк
        List<Line> linesList = new ArrayList<>();
        String line;
        // настраиваем сканер на построчное сканирование входного файла
        Scanner lineScanner = new Scanner(inputFile, StandardCharsets.UTF_8);

        int badLineCounter = 0; // счётчик "плохих" строк

        while (lineScanner.hasNextLine()) { // выполнять пока есть следующая строка
            Line series = new Line();       // новая последовательность (строка)

            line = lineScanner.nextLine();  // выделяем следующую строку
            boolean isBadLine = false;
            Scanner fieldScanner = new Scanner(line);   // настраиваем сканер полей в строке
            fieldScanner.useDelimiter(";");             // назначаем в качестве разделителя символ ';'

            while (fieldScanner.hasNext()) {            // выполнять пока есть следующее поле
                if(!series.add(fieldScanner.next())) {  // выделенное поле добавляем в коллекцию строки
                    isBadLine = true;   // если поле некорректное, поднять флаг и
                    badLineCounter++;   // инкрементировать счётчик
                    break;              // сканировать остальные поля строки нет смысла, выход из цикла
                }
            }
            fieldScanner.close();

            // если полученная строка корректная, то добавляем её в коллекцию строк
            if(!isBadLine) linesList.add(series);
        }

        lineScanner.close();
        // Краткий отчёт в консоль программы
        System.out.printf("\nВ файле обработано %d строк(а/и), из них:\n", linesList.size()+badLineCounter);
        System.out.println("   - пригодных: " + linesList.size() + ";");
        System.out.println("   - негодных:  " + badLineCounter + ".");

        // Метод возвращает объект содержащий, коллекцию корректных строк, выделенных из входного файла парсером,
        // и количество не корректных строк
        return new LinesReport(linesList, badLineCounter);
    }
}
