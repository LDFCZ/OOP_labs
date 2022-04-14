package services;


import dao.CarDAO;
import dao.CarDAOImpl;
import products.Accessories;
import products.Car;
import products.CarBody;
import products.Engine;

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
