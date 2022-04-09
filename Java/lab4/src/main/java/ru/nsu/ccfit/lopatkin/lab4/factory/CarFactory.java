package ru.nsu.ccfit.lopatkin.lab4.factory;

import org.apache.log4j.Logger;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Supply;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.Task;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.ThreadPool;
import ru.nsu.ccfit.lopatkin.lab4.utils.factory.Storage;

import java.io.IOException;
import java.util.Properties;

public class CarFactory {

    private static final Logger log = Logger.getLogger(CarFactory.class);

    private Properties properties;

    private final Storage<Engine> engineStorage;
    private final Storage<CarBody> carBodyStorage;
    private final Storage<Accessories> accessoriesStorage;
    private final Storage<Car> carStorage;

    private final int dealerCount;
    private final int workerCount;
    private final int supplierCount;

    private final ThreadPool accessoriesSupplierThreadPool;
    private final ThreadPool engineSupplierThreadPool;
    private final ThreadPool carBodySupplierThreadPool;
    private final ThreadPool workerThreadPool;
    private final ThreadPool dealerThreadPool;

    private Task supplyAccessories;
    private Task supplyEngine;
    private Task supplyCarBody;
    private Task sellingOrder;
    private Task buildingOrder;

    //TODO produced car counter?

    public CarFactory() {
        try {
            properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        carBodyStorage = new Storage<>(Integer.parseInt(properties.getProperty("CarBodyStorageCapacity")), "Car body Storage");
        engineStorage = new Storage<>(Integer.parseInt(properties.getProperty("EngineStorageCapacity")), "Engine Storage");
        accessoriesStorage = new Storage<>(Integer.parseInt(properties.getProperty("AccessoriesStorageCapacity")), "Accessories Storage");
        carStorage = new Storage<>(Integer.parseInt(properties.getProperty("CarStorageCapacity")), "Car Storage");

        dealerCount = Integer.parseInt(properties.getProperty("NumberOfDealers"));
        workerCount = Integer.parseInt(properties.getProperty("NumberOfWorkers"));
        supplierCount = Integer.parseInt(properties.getProperty("NumberOfSuppliers"));

        accessoriesSupplierThreadPool = new ThreadPool(supplierCount);
        engineSupplierThreadPool = new ThreadPool(1);
        carBodySupplierThreadPool = new ThreadPool(1);
        workerThreadPool = new ThreadPool(workerCount);
        dealerThreadPool = new ThreadPool(dealerCount);

        supplyAccessories = new Supply<>(this, accessoriesStorage, 3000, Accessories.class);
        supplyEngine = new Supply<>(this, engineStorage, 3000, Engine.class);
        supplyCarBody = new Supply<>(this, carBodyStorage, 3000, CarBody.class);

        Thread routine = new Thread(() -> {
            while (carStorage.getOccupancy() < carStorage.getStorageCapacity()) {
                accessoriesSupplierThreadPool.addTask(supplyAccessories);
                engineSupplierThreadPool.addTask(supplyEngine);
                carBodySupplierThreadPool.addTask(supplyCarBody);
                //workerThreadPool.addTask();
            }
        });
    }

    public int getDealerCount() {
        return dealerCount;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public int getSupplierCount() {
        return supplierCount;
    }
}
