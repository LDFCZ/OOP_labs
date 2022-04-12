package ru.nsu.ccfit.lopatkin.lab4.tasks;

import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;


public class SellCar implements Task {

    private final Storage<Car> storage;

    private volatile int delay;

    public SellCar(Storage<Car> storage, int delay) {
        this.storage = storage;
        this.delay = delay;
    }

    @Override
    public void performWork() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int d = delay;
                Thread.sleep(d);
                storage.get();
            } catch (InterruptedException e) {
                break;
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
