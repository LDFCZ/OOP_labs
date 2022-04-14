package dao;

import products.Accessories;
import products.Car;
import products.CarBody;
import products.Engine;

public interface CarDAO {
    Car findById(int id);

    void producedCar(Car car, CarBody carBody, Engine engine, Accessories accessories);

    void delete(Car car);

}
