package ru.nsu.ccfit.lopatkin.lab4.factory;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.tasks.BuildCar;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.ThreadPool;

import java.util.ArrayDeque;

@Slf4j
public class CarStorage {
    public static final String STORAGE_WAS_INTERRUPTED = "Storage was interrupted!";

    private final ArrayDeque<Car> items;
    private final int storageCapacity;
    private final ThreadPool threadPool;

    private final Storage<Accessories> accessoriesStorage;
    private final Storage<CarBody> carBodyStorage;
    private final Storage<Engine> engineStorage;



    public CarStorage(int capacity, ThreadPool threadPool, Storage<Accessories> accessoriesStorage, Storage<CarBody> carBodyStorage, Storage<Engine> engineStorage) {
        storageCapacity = capacity;
        this.threadPool = threadPool;

        this.accessoriesStorage = accessoriesStorage;
        this.carBodyStorage = carBodyStorage;
        this.engineStorage = engineStorage;

        items = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            threadPool.addTask(new BuildCar(accessoriesStorage, carBodyStorage, engineStorage, this));
        }
    }

    public double getOccupancy() {
        return items.size() / (double)storageCapacity;
    }

    public int getStorageCapacity() { return  storageCapacity; }

    public synchronized Car get() {
        while (items.size() < 1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                log.info(STORAGE_WAS_INTERRUPTED);
            }
        }
        Car car = items.removeLast();
        threadPool.addTask(new BuildCar(accessoriesStorage, carBodyStorage, engineStorage, this));
        notify();
        return car;
    }

    public synchronized void put(Car car) {
        while (items.size() >= storageCapacity) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                log.info(STORAGE_WAS_INTERRUPTED);
            }
        }

        items.add(car);
        notify();
    }
}
