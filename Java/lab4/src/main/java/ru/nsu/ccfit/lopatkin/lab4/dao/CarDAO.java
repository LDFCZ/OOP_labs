package ru.nsu.ccfit.lopatkin.lab4.dao;

import ru.nsu.ccfit.lopatkin.lab4.products.Car;

import java.util.List;

public interface CarDAO {

    Car findCarByID(long id);

    long produceCar(Car car);

    void deleteCar(Car car);

    List<Car> findAll();
}
