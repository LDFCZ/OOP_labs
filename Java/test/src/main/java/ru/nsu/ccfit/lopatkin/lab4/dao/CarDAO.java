package ru.nsu.ccfit.lopatkin.lab4.dao;

import ru.nsu.ccfit.lopatkin.lab4.products.Car;

import java.util.List;

public interface CarDAO {
    Car findById(int id);

    void producedCar(Car car);

    void delete(Car car);

}
