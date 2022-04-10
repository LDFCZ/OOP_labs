package ru.nsu.ccfit.lopatkin.lab4.service;

import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAO;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;

public class CarService {

    private final CarDAO carDAO = new CarDAOImpl();

    public Car findCar(int id) {
        return carDAO.findById(id);
    }

    public void producedCar(Car car) {
        carDAO.producedCar(car);
    }

    public void delete(Car car) {
        carDAO.delete(car);
    }

}
