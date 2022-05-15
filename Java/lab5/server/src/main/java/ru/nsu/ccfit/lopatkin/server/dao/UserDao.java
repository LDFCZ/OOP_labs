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

    public void save(User user) throws SaveUserException {
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/users.csv", true))){
            String[] record = {user.getName(), user.getPassword()};
            writer.writeNext(record);
            writer.flush();
        } catch (IOException e) {
            throw new SaveUserException("DaraBaseSaving EXCEPTION!");
        }
    }

    public User findByName(String name) throws FindUserException {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/users.csv"))) {
            List<String[]> r = reader.readAll();
            for (String[] s : r ) {
                if (s[0].equals(name)) return new User(s[0], s[1]);
            }
        } catch (IOException | CsvException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new FindUserException("DataBaseReading EXCEPTION!");
        }
        return null;
    }
}
