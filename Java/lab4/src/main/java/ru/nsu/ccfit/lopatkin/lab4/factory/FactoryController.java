package ru.nsu.ccfit.lopatkin.lab4.factory;

import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.service.CarService;
import ru.nsu.ccfit.lopatkin.lab4.tasks.SellCar;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.ThreadPool;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class FactoryController {

    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String NUMBER_OF_DEALERS = "NumberOfDealers";
    private static final String NUMBER_OF_WORKERS = "NumberOfWorkers";
    private static final String NUMBER_OF_SUPPLIERS = "NumberOfSuppliers";
    private static final String CAR_BODY_STORAGE_CAPACITY = "CarBodyStorageCapacity";
    private static final String ENGINE_STORAGE_CAPACITY = "EngineStorageCapacity";
    private static final String ACCESSORIES_STORAGE_CAPACITY = "AccessoriesStorageCapacity";
    private static final String CAR_STORAGE_CAPACITY = "CarStorageCapacity";
    private static final String ACCESSORIES_SUPPLIERS = "Accessories Suppliers";
    private static final String ACCESSORIES_SUPPLIER = "Accessories Supplier";
    private static final String ENGINE_SUPPLIERS = "Engine Suppliers";
    private static final String ENGINE_SUPPLIER = "Engine Supplier";
    private static final String CAR_BODY_SUPPLIERS = "Car Body Suppliers";
    private static final String CAR_BODY_SUPPLIER = "Car Body Supplier";
    private static final String WORKERS = "Workers";
    private static final String WORKER = "Worker";
    private static final String DEALERS = "Dealers";
    private static final String DEALER = "Dealer";
    private static final int START_DELAY = 3000;

    private Properties properties;

    private AtomicLong soldCarCounter;

    private Storage<Engine> engineStorage;
    private Storage<CarBody> carBodyStorage;
    private Storage<Accessories> accessoriesStorage;
    private CarStorage carStorage;

    private final int dealerCount;
    private final int workerCount;
    private final int accessoriesSupplierCount;

    private ThreadPool accessoriesSupplierThreadPool;
    private ThreadPool engineSupplierThreadPool;
    private ThreadPool carBodySupplierThreadPool;
    private ThreadPool workerThreadPool;
    private ThreadPool dealerThreadPool;

    public ThreadPool getEngineSupplierThreadPool() {
        return engineSupplierThreadPool;
    }

    public ThreadPool getAccessoriesSupplierThreadPool() {
        return accessoriesSupplierThreadPool;
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

    public FactoryController() {
        try {
            properties = new Properties();
            properties.load(this.getClass().getResourceAsStream(CONFIG_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        CarService carService = new CarService();
        soldCarCounter = new AtomicLong(carService.findAll().size());

        dealerCount = Integer.parseInt(properties.getProperty(NUMBER_OF_DEALERS));
        workerCount = Integer.parseInt(properties.getProperty(NUMBER_OF_WORKERS));
        accessoriesSupplierCount = Integer.parseInt(properties.getProperty(NUMBER_OF_SUPPLIERS));

        createThreadPools();
        createStorages();
    }

    private void createStorages() {
        carBodyStorage = new Storage<>(Integer.parseInt(properties.getProperty(CAR_BODY_STORAGE_CAPACITY)), carBodySupplierThreadPool, CarBody.class);
        engineStorage = new Storage<>(Integer.parseInt(properties.getProperty(ENGINE_STORAGE_CAPACITY)), engineSupplierThreadPool ,Engine.class);
        accessoriesStorage = new Storage<>(Integer.parseInt(properties.getProperty(ACCESSORIES_STORAGE_CAPACITY)), accessoriesSupplierThreadPool ,Accessories.class);
        carStorage = new CarStorage(Integer.parseInt(properties.getProperty(CAR_STORAGE_CAPACITY)), workerThreadPool ,accessoriesStorage, carBodyStorage, engineStorage);
    }

    private void createThreadPools() {
        accessoriesSupplierThreadPool = new ThreadPool(accessoriesSupplierCount, ACCESSORIES_SUPPLIERS, ACCESSORIES_SUPPLIER, START_DELAY);
        engineSupplierThreadPool = new ThreadPool(1, ENGINE_SUPPLIERS, ENGINE_SUPPLIER, START_DELAY);
        carBodySupplierThreadPool = new ThreadPool(1, CAR_BODY_SUPPLIERS, CAR_BODY_SUPPLIER, START_DELAY);
        workerThreadPool = new ThreadPool(workerCount, WORKERS, WORKER, START_DELAY);
        dealerThreadPool = new ThreadPool(dealerCount, DEALERS, DEALER, START_DELAY);
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

    public CarStorage getCarStorage() {
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

    public void addSellTask() {
        dealerThreadPool.addTask(new SellCar(this, carStorage));
    }

}
