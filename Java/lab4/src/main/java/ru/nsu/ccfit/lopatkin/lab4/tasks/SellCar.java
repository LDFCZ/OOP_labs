package ru.nsu.ccfit.lopatkin.lab4.tasks;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryController;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;

@Slf4j
public class SellCar implements Task {

    private final FactoryController factoryController;

    private final Storage<Car> storage;

    private volatile int delay;

    public SellCar(FactoryController factoryController, Storage<Car> storage, int delay) {
        this.factoryController = factoryController;
        this.storage = storage;
        this.delay = delay;
    }

    @Override
    public void performWork(String threadName) throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int d = delay;
                Thread.sleep(d);
                Car carForSale = storage.get();
                factoryController.sellCar();
                log.info("Car " + carForSale.getFullVin() + " SOLD! Congratulations to the " + threadName + " GOOD JOB!");
            } catch (InterruptedException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void changeDelay(int newDelay) {
        delay = newDelay;
    }

}
