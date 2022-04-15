package ru.nsu.ccfit.lopatkin.lab4.tasks;


import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.products.CarPart;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;

import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;
import ru.nsu.ccfit.lopatkin.lab4.service.ProductServiceAbstract;

@Slf4j
public class Supply<T extends Product & CarPart> implements Task {
    private final Storage<T> storage;
    private final ProductServiceAbstract<T> productService = new ProductServiceAbstract<>();

    private int delay;

    private final Class<T> productType;

    public Supply(Storage<T> storage, int delay, Class<T> productType) {
        this.storage = storage;
        this.delay = delay;
        this.productType = productType;
    }

    @Override
    public void performWork(String threadName) throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int d = delay;
                Thread.sleep(d);
                T product = productType.getDeclaredConstructor().newInstance();
                productService.produceProduct(product);
                storage.put(product);
                log.info(threadName + " supplied " + product.getFullVin());
            } catch (InterruptedException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void changeDelay(int newDelay) {
        delay = newDelay;
    }
}