package org.example;

import java.util.List;

public class LinesReport {
    private List<Line> lines;
    private int badLines;

    public LinesReport(List<Line> lines, int badLines) {
        this.lines = lines;
        this.badLines = badLines;
    }

    public List<Line> getLines() {
        return lines;
    }

    public int getBadLines() {
        return badLines;
    }
}
