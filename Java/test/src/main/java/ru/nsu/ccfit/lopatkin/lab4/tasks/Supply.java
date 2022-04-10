package ru.nsu.ccfit.lopatkin.lab4.tasks;


import ru.nsu.ccfit.lopatkin.lab4.products.Product;

import ru.nsu.ccfit.lopatkin.lab4.factory.Storage;

public class Supply<T extends Product> implements Task {
    private final Storage<T> storage;

    private int delay;

    private final Class<T> productType;

    public Supply(Storage<T> storage, int delay, Class<T> productType) {
        this.storage = storage;
        this.delay = delay;
        this.productType = productType;
    }

    @Override
    public void performWork() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int d = delay;
                Thread.sleep(d);
                T product = productType.getDeclaredConstructor().newInstance();
                storage.put(product);
            } catch (InterruptedException e) {
                break;
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
