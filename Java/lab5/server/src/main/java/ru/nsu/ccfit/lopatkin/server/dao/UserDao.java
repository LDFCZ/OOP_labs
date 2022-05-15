package ru.nsu.ccfit.lopatkin.server.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import ru.nsu.ccfit.lopatkin.server.exceptions.FindUserException;
import ru.nsu.ccfit.lopatkin.server.exceptions.SaveUserException;
import ru.nsu.ccfit.lopatkin.server.models.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class UserDao {

    public static final String USERS_CSV = "src/main/resources/users.csv";
    public static final int NAME = 0;
    public static final int PASSWORD = 1;
    public static final String DATA_BASE_READING_EXCEPTION = "DataBaseReading EXCEPTION!";

    public void save(User user) throws SaveUserException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(USERS_CSV, true))){
            String[] record = {user.getName(), user.getPassword()};
            writer.writeNext(record);
            writer.flush();
        } catch (IOException e) {
            throw new SaveUserException("DaraBaseSaving EXCEPTION!");
        }
    }

    public User findByName(String name) throws FindUserException {
        try (CSVReader reader = new CSVReader(new FileReader(USERS_CSV))) {
            List<String[]> r = reader.readAll();
            for (String[] s : r ) {
                if (s[NAME].equals(name)) return new User(s[NAME], s[PASSWORD]);
            }
        } catch (IOException | CsvException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new FindUserException(DATA_BASE_READING_EXCEPTION);
        }
        return null;
    }
}
