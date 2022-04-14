package ru.nsu.ccfit.lopatkin.lab4.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAO;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;


@Service
@ComponentScan(basePackages = "ru.nsu.ccfit.lopatkin.lab4")
public class CarService {
    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CarService.class);

    //private final CarDAO carDAO = new CarDAOImpl();

    public Car findCar(int id) { return context.getBean(CarDAOImpl.class).findCarByID(id); }

    public void produceCar(Car car) {
        context.getBean(CarDAOImpl.class).produceCar(car);
    }

    public void deleteCar(Car car) {
        context.getBean(CarDAOImpl.class).deleteCar(car);
    }

}
