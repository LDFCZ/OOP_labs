package ru.nsu.ccfit.lopatkin.lab4.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;


@Service
public class CarService {


    private final CarDAOImpl carDAO;

    public CarService(CarDAOImpl carDAO) {
        this.carDAO = carDAO;
    }


    public Car findCar(int id) { return carDAO.findCarByID(id); }

    public void produceCar(Car car) {
        carDAO.produceCar(car);
    }

    public void deleteCar(Car car) {
        carDAO.deleteCar(car);
    }

}
