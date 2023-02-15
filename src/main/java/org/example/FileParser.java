package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileParser {
    public static LinesReport parsing(File inputFile) throws IOException {

        List<Line> linesList = new ArrayList<>();
        String line;
        Scanner lineScanner = new Scanner(inputFile, StandardCharsets.UTF_8);

        int badLineCounter = 0;

        while (lineScanner.hasNextLine()) {
            Line series = new Line();
            line = lineScanner.nextLine();

            boolean isBadLine = false;
            Scanner fieldScanner = new Scanner(line);
            fieldScanner.useDelimiter(";");

            while (fieldScanner.hasNext()) {
                if(!series.add(fieldScanner.next())) {
                    isBadLine = true;
                    badLineCounter++;
                    break;
                }
            }
            fieldScanner.close();

            if(!isBadLine) linesList.add(series);
        }

        lineScanner.close();

        System.out.printf("\nВ файле обработано %d строк(а/и), из них:\n", linesList.size()+badLineCounter);
        System.out.println("   - пригодных: " + linesList.size() + ";");
        System.out.println("   - негодных:  " + badLineCounter + ".");

        return new LinesReport(linesList, badLineCounter);
    }
}
