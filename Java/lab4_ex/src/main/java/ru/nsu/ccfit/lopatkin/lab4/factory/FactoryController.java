package ru.nsu.ccfit.lopatkin.lab4.factory;


import org.springframework.stereotype.Controller;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAO;
import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.tasks.BuildCar;
import ru.nsu.ccfit.lopatkin.lab4.tasks.SellCar;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Supply;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Task;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.ThreadPool;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class FactoryController {

    private Properties properties;

    private volatile AtomicLong soldCarCounter;

    private Storage<Engine> engineStorage;
    private Storage<CarBody> carBodyStorage;
    private Storage<Accessories> accessoriesStorage;
    private Storage<Car> carStorage;

    private final int dealerCount;
    private final int workerCount;
    private final int accessoriesSupplierCount;

    private ThreadPool accessoriesSupplierThreadPool;
    private ThreadPool engineSupplierThreadPool;
    private ThreadPool carBodySupplierThreadPool;
    private ThreadPool workerThreadPool;
    private ThreadPool dealerThreadPool;

    private Task supplyAccessories;
    private Task supplyEngine;
    private Task supplyCarBody;
    private Task sellCar;
    private Task buildCar;

    public FactoryController() {
        try {
            properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CarDAO carDAO = new CarDAOImpl();
        soldCarCounter = new AtomicLong(carDAO.findAll().size());

        createStorages();

        dealerCount = Integer.parseInt(properties.getProperty("NumberOfDealers"));
        workerCount = Integer.parseInt(properties.getProperty("NumberOfWorkers"));
        accessoriesSupplierCount = Integer.parseInt(properties.getProperty("NumberOfSuppliers"));

        createThreadPools();

        createSuppliers();
        runSuppliers();

        runDealers();

        runWorkers();
    }

    private void createStorages() {
        carBodyStorage = new Storage<>(Integer.parseInt(properties.getProperty("CarBodyStorageCapacity")));
        engineStorage = new Storage<>(Integer.parseInt(properties.getProperty("EngineStorageCapacity")));
        accessoriesStorage = new Storage<>(Integer.parseInt(properties.getProperty("AccessoriesStorageCapacity")));
        carStorage = new Storage<>(Integer.parseInt(properties.getProperty("CarStorageCapacity")));
    }

    private void createThreadPools() {
        accessoriesSupplierThreadPool = new ThreadPool(accessoriesSupplierCount, "Accessories Suppliers", "Accessories Supplier");
        engineSupplierThreadPool = new ThreadPool(1, "Engine Suppliers", "Engine Supplier");
        carBodySupplierThreadPool = new ThreadPool(1, "Car Body Suppliers", "Car Body Supplier");
        workerThreadPool = new ThreadPool(workerCount, "Workers", "Worker");
        dealerThreadPool = new ThreadPool(dealerCount, "Dealers", "Dealer");
    }

    private void createSuppliers() {
        supplyAccessories = new Supply<>(accessoriesStorage, 3000, Accessories.class);
        supplyEngine = new Supply<>(engineStorage, 3000, Engine.class);
        supplyCarBody = new Supply<>(carBodyStorage, 3000, CarBody.class);
    }

    private void runSuppliers() {
        for (int i = 0; i < accessoriesSupplierCount; i++)
            accessoriesSupplierThreadPool.addTask(supplyAccessories);
        engineSupplierThreadPool.addTask(supplyEngine);
        carBodySupplierThreadPool.addTask(supplyCarBody);
    }



    private void runDealers() {
        sellCar = new SellCar(this, carStorage, 3000);

        for (int i = 0; i < dealerCount; i++) {
            dealerThreadPool.addTask(sellCar);
        }
    }

    private void runWorkers() {
        buildCar = new BuildCar(3000, accessoriesStorage, carBodyStorage, engineStorage, carStorage);

        for (int i = 0; i < workerCount; i++) {
            workerThreadPool.addTask(buildCar);
        }
    }

    public void stopFactory() {
        workerThreadPool.shutdown();
        accessoriesSupplierThreadPool.shutdown();
        engineSupplierThreadPool.shutdown();
        carBodySupplierThreadPool.shutdown();
        dealerThreadPool.shutdown();
    }

    public void sellCar() {
        soldCarCounter.getAndIncrement();
    }

    public long getSoldCarCounter() {
        return soldCarCounter.get();
    }

    public Storage<Engine> getEngineStorage() {
        return engineStorage;
    }

    public Storage<CarBody> getCarBodyStorage() { return carBodyStorage; }

    public Storage<Accessories> getAccessoriesStorage() {
        return accessoriesStorage;
    }

    public Storage<Car> getCarStorage() {
        return carStorage;
    }

    public int getDealerCount() {
        return dealerCount;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public int getAccessoriesSupplierCount() {
        return accessoriesSupplierCount;
    }

    public Task getSupplyAccessoriesTask() { return supplyAccessories; }

    public Task getSupplyEngineTask() { return supplyEngine; }

    public Task getSupplyCarBodyTask() { return supplyCarBody; }

    public Task getSellCarTask() { return sellCar; }

    public Task getBuildCarTask() { return buildCar; }
}