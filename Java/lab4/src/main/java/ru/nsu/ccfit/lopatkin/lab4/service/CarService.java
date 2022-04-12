package ru.nsu.ccfit.lopatkin.lab4.service;

import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAO;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;

public class CarService {

    private final CarDAO carDAO = new CarDAOImpl();

    public Car findCar(int id) {
        return carDAO.findById(id);
    }

    public void producedCar(Car car, CarBody carBody, Engine engine, Accessories accessories) {
        carDAO.producedCar(car, carBody, engine, accessories);
    }

    public void delete(Car car) {
        carDAO.delete(car);
    }

}
