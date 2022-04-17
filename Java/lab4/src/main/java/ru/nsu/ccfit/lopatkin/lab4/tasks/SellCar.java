package ru.nsu.ccfit.lopatkin.lab4.tasks;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.constSpace.ConstSpace;
import ru.nsu.ccfit.lopatkin.lab4.factory.CarStorage;
import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryController;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;

@Slf4j
public class SellCar implements Task {

    private static final String CAR = "Car ";
    private static final String CONGRATULATIONS = " SOLD! Congratulations to the ";
    private static final String GOOD_JOB = " GOOD JOB!";


    private final FactoryController factoryController;

    private final CarStorage storage;

    public SellCar(FactoryController factoryController, CarStorage storage) {
        this.factoryController = factoryController;
        this.storage = storage;
    }

    @Override
    public void performWork(String threadName, int delay) throws InterruptedException {
        try {
            Thread.sleep(delay);
            Car carForSale = storage.get();
            factoryController.sellCar();
            log.info(CAR + carForSale.getFullVin() + CONGRATULATIONS + threadName + GOOD_JOB);
        } catch (InterruptedException e) {
            log.info(threadName + ConstSpace.INTERRUPTED);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}
