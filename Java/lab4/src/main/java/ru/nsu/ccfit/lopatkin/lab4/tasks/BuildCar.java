package ru.nsu.ccfit.lopatkin.lab4.tasks;

import ru.nsu.ccfit.lopatkin.lab4.factory.CarFactory;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.Task;
import ru.nsu.ccfit.lopatkin.lab4.utils.factory.Storage;

public class BuildCar implements Task {
    private final CarFactory carFactory;
    private final int workerID; // for logging

    private final Storage<Accessories> engineStorage;
    private final Storage<CarBody> carBodyStorage;
    private final Storage<Engine> wheelStorage;
    private final Storage<Car> carStorage;

    private int delay;
    private float progress;

    public BuildCar(CarFactory carFactory, int workerID, int delay,
                    Storage<Accessories> engineStorage, Storage<CarBody> carBodyStorage, Storage<Engine> wheelStorage, Storage<Car> carStorage) {
        this.carFactory = carFactory;
        this.workerID = workerID;
        this.engineStorage = engineStorage;
        this.carBodyStorage = carBodyStorage;
        this.wheelStorage = wheelStorage;
        this.carStorage = carStorage;
    }


    @Override
    public String getTaskName() {
        return "Worker " + workerID;
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
                carStorage.put(new Car(0));
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
        delay= newDelay;
    }

    @Override
    public float getProgress() {
        return progress;
    }
}
