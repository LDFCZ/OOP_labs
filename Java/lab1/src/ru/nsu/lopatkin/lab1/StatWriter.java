package ru.nsu.lopatkin.lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatWriter {
    private final CSVWriter writer;
    private final Context context;

    public StatWriter(CSVWriter writer, Context context) {
        this.writer = writer;
        this.context = context;
    }

    public void writeStat() {
        List<Map.Entry<String, Integer>> words = context.getSortedList();
        int wordCount = context.getWordCount();

        ArrayList<String> CSVLine = new ArrayList<>();

        for (int i = words.size() - 1; i >= 0; i--) {
            CSVLine.add(words.get(i).getKey());
            CSVLine.add(words.get(i).getValue().toString());
            CSVLine.add(((Float)(((float)words.get(i).getValue()/wordCount)*100)).toString());
            try {
                writer.write(CSVLine);
            }
            catch (IOException ex) {
                System.out.print(ex.getMessage() + System.lineSeparator());
                ex.printStackTrace();
                return;
            }
            CSVLine.clear();
        }
    }
}
