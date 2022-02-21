package Java.lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private FileWriter fileWriter;

    static private final String separator = ",";

    public CSVWriter(String fileName) throws IOException{
        fileWriter = new FileWriter(fileName);
    }

    public void write(List<String> CSVLine) throws IOException {
        String result_CSV_line = String.join(separator, CSVLine);
        fileWriter.write(result_CSV_line + System.lineSeparator());
    }

    public void close() throws IOException {
        fileWriter.flush();
        fileWriter.close();
    }
}
