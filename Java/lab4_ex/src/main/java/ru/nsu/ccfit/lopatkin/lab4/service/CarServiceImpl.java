package ru.nsu.ccfit.lopatkin.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.repos.CarRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;


    //public CarServiceImpl(CarRepository carRepository) {
    //    this.carRepository = carRepository;
    //}
    //private final CarDAOImpl carDAO;

    //public CarService(CarDAOImpl carDAO) {
    //    this.carDAO = carDAO;
    //}

    @Override
    public Optional<Car> findCar(long id) { return carRepository.findById(id); }

    @Override
    public void produceCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCar(Car car) {
        carRepository.delete(car);
    }

    @Override
    public List<Car> findAll() {
        return (List<Car>) carRepository.findAll();
    }

}
