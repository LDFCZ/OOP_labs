package ru.nsu.ccfit.lopatkin.lab4.tasks;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;
import ru.nsu.ccfit.lopatkin.lab4.service.CarService;

@Slf4j
public class BuildCar implements Task {
    private final CarService carService = new CarService();

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
    public void performWork() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int d = delay;
                Thread.sleep(d);
                Car newCar = new Car(carBodyStorage.get() ,engineStorage.get(), accessoriesStorage.get());
                carStorage.put(newCar);
                carService.producedCar(newCar);
                log.info("Produced car: " + newCar.getFullVin());
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
