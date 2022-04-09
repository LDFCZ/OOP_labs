package ru.nsu.ccfit.lopatkin.lab4.factory;

import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.tasks.BuildCar;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.ThreadPool;

public class FactoryController implements Runnable {
    private volatile int buildCarDelay;

    private final Storage<Accessories> accessoriesStorage;
    private final Storage<CarBody> carBodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Car> carStorage;
    private final ThreadPool workersThreadPool;

    public FactoryController(Storage<Accessories> accessoriesStorage, Storage<CarBody> carBodyStorage, Storage<Engine> engineStorage, Storage<Car> carStorage,
                             ThreadPool workersThreadPool, int buildCarDelay) {
        this.accessoriesStorage = accessoriesStorage;
        this.carBodyStorage = carBodyStorage;
        this.engineStorage = engineStorage;
        this.carStorage = carStorage;
        this.workersThreadPool = workersThreadPool;
        this.buildCarDelay = buildCarDelay;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            workersThreadPool.addTask(new BuildCar(buildCarDelay, accessoriesStorage, carBodyStorage, engineStorage, carStorage));
        }
    }

    public void setBuildCarDelay(int delay) {
        buildCarDelay = delay;
    }
}
