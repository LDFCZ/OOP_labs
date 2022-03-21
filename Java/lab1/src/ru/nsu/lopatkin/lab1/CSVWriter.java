package ru.nsu.lopatkin.lab1;

import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter implements Closeable {
    private final FileWriter fileWriter;

    static private final String separator = ",";

    public CSVWriter(String fileName) throws IOException{
        fileWriter = new FileWriter(fileName);
    }

    public void write(List<String> csvLine) throws IOException {
        String result_CSV_line = String.join(separator, csvLine);
        fileWriter.write(result_CSV_line + System.lineSeparator());
    }

    @Override
    public void close() throws IOException {
        fileWriter.flush();
        fileWriter.close();
    }
}
