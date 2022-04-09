package ru.nsu.ccfit.lopatkin.lab4.tasks;

import ru.nsu.ccfit.lopatkin.lab4.factory.FactoryController;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;

public class BuildCar implements Task {

    private final int workerID; // for logging

    private final Storage<Accessories> accessoriesStorage;
    private final Storage<CarBody> carBodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Car> carStorage;

    private int delay;
    private float progress;

    public BuildCar(int delay, Storage<Accessories> accessoriesStorage, Storage<CarBody> carBodyStorage, Storage<Engine> engineStorage, Storage<Car> carStorage) {

        this.workerID = 0;
        this.accessoriesStorage = accessoriesStorage;
        this.carBodyStorage = carBodyStorage;
        this.engineStorage = engineStorage;
        this.carStorage = carStorage;
    }


    @Override
    public String getTaskName() {
        return "Worker " + workerID;
    }

    @Override
    public void performWork() throws InterruptedException {
        try {
            int d = delay;
            for (int i = 0; i < d; i++) {
                Thread.sleep(1);  //I know, this is very bad, but I want to do a progress bar :)
                progress = i/(float)d;
            }
            accessoriesStorage.get();
            engineStorage.get();
            carBodyStorage.get();
            carStorage.put(new Car(0));
            // TODO put all in the car
        } catch (InterruptedException e) {
            // TODO interrupted
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
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
