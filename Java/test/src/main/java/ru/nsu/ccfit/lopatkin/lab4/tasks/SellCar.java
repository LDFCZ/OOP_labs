package ru.nsu.ccfit.lopatkin.lab4.tasks;

import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryController;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;

public class SellCar implements Task {
    private final int dealerID;
    private final FactoryController factoryController;
    private final Storage<Car> storage;

    private volatile int delay;
    private volatile float progress;

    public SellCar(int dealerID, FactoryController factoryController, Storage<Car> storage, int delay) {
        this.dealerID = dealerID;
        this.factoryController = factoryController;
        this.storage = storage;
        this.delay = delay;
    }


    @Override
    public String getTaskName() {
        return "Dealer " + dealerID;
    }

    @Override
    public void performWork() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {

            try {
                int d = delay;
                for (int i = 0; i < d; i++) {
                    Thread.sleep(1);  //I know, this is very bad, but I want to do a progress bar :)
                    progress = i/(float)d;
                }
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

    @Override
    public float getProgress() {
        return progress;
    }
}
