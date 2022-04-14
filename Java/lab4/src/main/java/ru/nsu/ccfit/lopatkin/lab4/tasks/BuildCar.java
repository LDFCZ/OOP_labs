package ru.nsu.ccfit.lopatkin.lab4.tasks;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;
import ru.nsu.ccfit.lopatkin.lab4.service.CarService;
import ru.nsu.ccfit.lopatkin.lab4.service.ProductService;

@Slf4j
public class BuildCar implements Task {
    private final CarService carService = new CarService();

    private final ProductService<Accessories> accessoriesProductService = new ProductService<>();
    private final ProductService<CarBody> carBodyProductService = new ProductService<>();
    private final ProductService<Engine> engineProductService = new ProductService<>();

    private final Storage<Accessories> accessoriesStorage;
    private final Storage<CarBody> carBodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Car> carStorage;

    private int delay;

    public BuildCar(int delay, Storage<Accessories> accessoriesStorage, Storage<CarBody> carBodyStorage, Storage<Engine> engineStorage, Storage<Car> carStorage) {
        this.delay = delay;
        this.accessoriesStorage = accessoriesStorage;
        this.carBodyStorage = carBodyStorage;
        this.engineStorage = engineStorage;
        this.carStorage = carStorage;
    }

    @Override
    public void performWork(String threadName) throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int d = delay;
                Thread.sleep(d);
                CarBody carBody = carBodyStorage.get();
                Engine engine = engineStorage.get();
                Accessories accessories = accessoriesStorage.get();
                Car newCar = new Car(carBody, engine, accessories);
                carStorage.put(newCar);
                carService.produceCar(newCar);
                accessoriesProductService.updateUsedCar(accessories, newCar, Accessories.class);
                carBodyProductService.updateUsedCar(carBody, newCar, CarBody.class);
                engineProductService.updateUsedCar(engine, newCar, Engine.class);
                log.info(threadName + " produced car: " + newCar.getFullVin() + newCar.getProductID());
            } catch (InterruptedException e) {
                // TODO interrupted
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

}
