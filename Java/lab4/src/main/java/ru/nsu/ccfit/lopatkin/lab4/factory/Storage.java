package ru.nsu.ccfit.lopatkin.lab4.factory;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;

import java.util.ArrayDeque;

@Slf4j
public class Storage<T extends Product> {
    public static final String STORAGE_WAS_INTERRUPTED = "Storage was interrupted!";

    private final ArrayDeque<T> items;
    private final int storageCapacity;


    public Storage(int capacity) {
        storageCapacity = capacity;
        items = new ArrayDeque<>();
    }

    public double getOccupancy() {
        return items.size() / (double)storageCapacity;
    }

    public int getStorageCapacity() { return  storageCapacity; }

    public synchronized T get() {
        while (items.size() < 1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                log.info(STORAGE_WAS_INTERRUPTED);
            }
        }
        T item = items.removeLast();
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
