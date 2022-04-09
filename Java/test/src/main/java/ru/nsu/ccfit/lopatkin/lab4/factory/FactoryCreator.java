package ru.nsu.ccfit.lopatkin.lab4.factory;


import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Supply;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Task;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.ThreadPool;

import java.io.IOException;
import java.util.Properties;

public class FactoryCreator {

    private Properties properties;

    private Storage<Engine> engineStorage;
    private Storage<CarBody> carBodyStorage;
    private Storage<Accessories> accessoriesStorage;
    private Storage<Car> carStorage;

    private final int dealerCount;
    private final int workerCount;
    private final int supplierCount;

    private ThreadPool accessoriesSupplierThreadPool;
    private ThreadPool engineSupplierThreadPool;
    private ThreadPool carBodySupplierThreadPool;
    private ThreadPool workerThreadPool;
    private ThreadPool dealerThreadPool;

    private Task supplyAccessories;
    private Task supplyEngine;
    private Task supplyCarBody;
    private Task sellCar;

    public Storage<Engine> getEngineStorage() {
        return engineStorage;
    }

    public Storage<CarBody> getCarBodyStorage() {
        return carBodyStorage;
    }

    public Storage<Accessories> getAccessoriesStorage() {
        return accessoriesStorage;
    }

    public Storage<Car> getCarStorage() {
        return carStorage;
    }

    public ThreadPool getAccessoriesSupplierThreadPool() {
        return accessoriesSupplierThreadPool;
    }

    public ThreadPool getEngineSupplierThreadPool() {
        return engineSupplierThreadPool;
    }

    public ThreadPool getCarBodySupplierThreadPool() {
        return carBodySupplierThreadPool;
    }

    public ThreadPool getWorkerThreadPool() {
        return workerThreadPool;
    }

    public ThreadPool getDealerThreadPool() {
        return dealerThreadPool;
    }

    public FactoryCreator() {
        try {
            properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        createStorages();

        dealerCount = Integer.parseInt(properties.getProperty("NumberOfDealers"));
        workerCount = Integer.parseInt(properties.getProperty("NumberOfWorkers"));
        supplierCount = Integer.parseInt(properties.getProperty("NumberOfSuppliers"));

        createThreadPools();

        createSuppliers();
        runSuppliers();
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

    private void createStorages() {
        carBodyStorage = new Storage<>(Integer.parseInt(properties.getProperty("CarBodyStorageCapacity")), "Car body Storage");
        engineStorage = new Storage<>(Integer.parseInt(properties.getProperty("EngineStorageCapacity")), "Engine Storage");
        accessoriesStorage = new Storage<>(Integer.parseInt(properties.getProperty("AccessoriesStorageCapacity")), "Accessories Storage");
        carStorage = new Storage<>(Integer.parseInt(properties.getProperty("CarStorageCapacity")), "Car Storage");
    }

    private void createThreadPools() {
        accessoriesSupplierThreadPool = new ThreadPool(supplierCount);
        engineSupplierThreadPool = new ThreadPool(1);
        carBodySupplierThreadPool = new ThreadPool(1);
        workerThreadPool = new ThreadPool(workerCount, workerCount);
        dealerThreadPool = new ThreadPool(dealerCount);
    }

    private void createSuppliers() {
        supplyAccessories = new Supply<>(accessoriesStorage, 3000, Accessories.class);
        supplyEngine = new Supply<>(engineStorage, 3000, Engine.class);
        supplyCarBody = new Supply<>(carBodyStorage, 3000, CarBody.class);
    }

    private void runSuppliers() {
        accessoriesSupplierThreadPool.addTask(supplyAccessories);
        engineSupplierThreadPool.addTask(supplyEngine);
        carBodySupplierThreadPool.addTask(supplyCarBody);
    }

    private void runDealers() {

    }
}
