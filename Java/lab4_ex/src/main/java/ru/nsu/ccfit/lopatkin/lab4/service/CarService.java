package ru.nsu.ccfit.lopatkin.lab4.service;

import ru.nsu.ccfit.lopatkin.lab4.products.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Optional<Car> findCar(long id);

    void produceCar(Car car);

    void deleteCar(Car car);

    List<Car> findAll();

}
