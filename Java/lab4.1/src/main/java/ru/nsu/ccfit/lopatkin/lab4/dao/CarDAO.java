package ru.nsu.ccfit.lopatkin.lab4.dao;

import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;

public interface CarDAO {
    Car findById(int id);

    void producedCar(Car car, CarBody carBody, Engine engine, Accessories accessories);

    void delete(Car car);

}
