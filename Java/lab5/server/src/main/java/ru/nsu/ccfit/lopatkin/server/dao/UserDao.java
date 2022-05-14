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

    public long save(User user) throws SaveUserException {
        try (CSVWriter writer = new CSVWriter(new FileWriter("users.csv", true))){
            String[] record = {user.getName(), user.getPassword()};
            writer.writeNext(record);
        } catch (IOException e) {
            throw new SaveUserException("DaraBaseSaving EXCEPTION!");
        }
        return 0;
    }

    public User findByName(String name) throws FindUserException {
        try (CSVReader reader = new CSVReader(new FileReader("users.csv"))) {
            List<String[]> r = reader.readAll();
            for (String[] s : r ) {
                if (s[0].equals(name)) return new User(s[0], s[1]);
            }
        } catch (IOException | CsvException e) {
            throw new FindUserException("DataBaseReading EXCEPTION!");
        }
        return null;
    }
}
