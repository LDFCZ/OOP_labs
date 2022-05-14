package ru.nsu.ccfit.lopatkin.server.services;

import ru.nsu.ccfit.lopatkin.server.dao.UserDao;
import ru.nsu.ccfit.lopatkin.server.exceptions.FindUserException;
import ru.nsu.ccfit.lopatkin.server.exceptions.SaveUserException;
import ru.nsu.ccfit.lopatkin.server.models.User;

public class UserService {
    private static final UserDao userDao = new UserDao();

    private String exceptionMessage = "";
    public boolean createNewUser(String name, String password) {
        User newUser = new User(name, password);
        try {
            if (userDao.findByName(name) != null) {
                exceptionMessage = "Name: " + name + " is already used!";
                return false;
            }
            userDao.save(newUser);
        } catch (FindUserException | SaveUserException e) {
            exceptionMessage = e.getMessage();
            return false;
        }
        return true;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public User findUserByName(String name) throws FindUserException {
        return userDao.findByName(name);
    }
}
