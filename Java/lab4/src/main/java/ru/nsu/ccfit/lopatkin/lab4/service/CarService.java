package ru.nsu.ccfit.lopatkin.lab4.service;

import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAO;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;

import java.util.List;


@Service
public class CarService {

    // TODO ProductDAO
    private final CarDAO carDAO = new CarDAOImpl();

    public Car findCar(int id) {
        return carDAO.findCarByID(id);
    }

    public void produceCar(Car car) {
        carDAO.produceCar(car);
    }

    public void deleteCar(Car car) {
        carDAO.deleteCar(car);
    }

    public List<Car> findAll() {
        return carDAO.findAll();
    }

}
