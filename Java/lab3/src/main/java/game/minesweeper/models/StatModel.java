package game.minesweeper.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatModel {
    private static final String STAT_PATH = "src/main/resources/data.csv";
    private static final String CSV_SEPARATOR = ",";

    public List<String[]> getStat() {
        String line = "";
        List<String[]> outputData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(STAT_PATH));) {
            while ((line = br.readLine()) != null) {

                String[] data = line.split(CSV_SEPARATOR);
                outputData.add(data);
            }
        } catch (Exception e) {
            System.out.print("database problems\n");
        }

        return outputData;
    }

}
