package ru.nsu.ccfit.lopatkin.lab4.tasks;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.constSpace.ConstSpace;
import ru.nsu.ccfit.lopatkin.lab4.factory.CarStorage;
import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.service.CarService;
import ru.nsu.ccfit.lopatkin.lab4.service.ProductService;

@Slf4j
public class BuildCar implements Task {
    private static final String PRODUCED_CAR = " produced car: ";
    private final CarService carService = new CarService();

    private final ProductService<Accessories> accessoriesProductService = new ProductService<>();
    private final ProductService<CarBody> carBodyProductService = new ProductService<>();
    private final ProductService<Engine> engineProductService = new ProductService<>();

    private final Storage<Accessories> accessoriesStorage;
    private final Storage<CarBody> carBodyStorage;
    private final Storage<Engine> engineStorage;
    private final CarStorage carStorage;


    public BuildCar(Storage<Accessories> accessoriesStorage, Storage<CarBody> carBodyStorage, Storage<Engine> engineStorage, CarStorage carStorage) {

        this.accessoriesStorage = accessoriesStorage;
        this.carBodyStorage = carBodyStorage;
        this.engineStorage = engineStorage;
        this.carStorage = carStorage;
    }

    @Override
    public void performWork(String threadName, int delay) throws InterruptedException {
        try {
            Thread.sleep(delay);
            CarBody carBody = carBodyStorage.get();
            Engine engine = engineStorage.get();
            Accessories accessories = accessoriesStorage.get();
            Car newCar = new Car(carBody, engine, accessories);
            carStorage.put(newCar);
            carService.produceCar(newCar);
            accessoriesProductService.updateUsedCar(accessories, newCar, Accessories.class);
            carBodyProductService.updateUsedCar(carBody, newCar, CarBody.class);
            engineProductService.updateUsedCar(engine, newCar, Engine.class);
            log.info(threadName + PRODUCED_CAR + newCar.getFullVin() + newCar.getProductID());
        } catch (InterruptedException e) {
            log.info(threadName + ConstSpace.INTERRUPTED);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
