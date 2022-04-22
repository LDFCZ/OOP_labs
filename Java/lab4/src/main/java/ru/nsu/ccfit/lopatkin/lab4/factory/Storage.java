package ru.nsu.ccfit.lopatkin.lab4.factory;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.products.CarPart;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;
import ru.nsu.ccfit.lopatkin.lab4.tasks.Supply;
import ru.nsu.ccfit.lopatkin.lab4.threadpool.ThreadPool;

import java.util.ArrayDeque;

@Slf4j
public class Storage<T extends Product & CarPart> {
    public static final String STORAGE_WAS_INTERRUPTED = "Storage was interrupted!";

    private final ArrayDeque<T> items;
    private final int storageCapacity;
    private final ThreadPool threadPool;
    private T t;


    public Storage(int capacity, ThreadPool threadPool, Class<T> productType) {
        storageCapacity = capacity;

        this.threadPool = threadPool;
        items = new ArrayDeque<>();
        t = null;
        try {
            t = productType.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < capacity; i++) {
            threadPool.addTask(new Supply<>(this, (Class<T>) t.getClass()));
        }
    }

    public double getOccupancy() {
        return items.size() / (double)storageCapacity;
    }

    public int getStorageCapacity() { return  storageCapacity; }

    public synchronized T get() throws InterruptedException {
        while (items.size() < 1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                log.info(STORAGE_WAS_INTERRUPTED);
                throw e;
            }
        }
        T item = items.removeLast();
        threadPool.addTask(new Supply<>(this, (Class<T>)t.getClass()));
        notify();
        return item;
    }

    public synchronized void put(T item) {
        while (items.size() >= storageCapacity) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                log.info(STORAGE_WAS_INTERRUPTED);
            }
        }

        items.add(item);
        notify();
    }
}
