package ru.nsu.ccfit.lopatkin.lab4.factory;

import ru.nsu.ccfit.lopatkin.lab4.products.Product;

import java.util.ArrayDeque;

public class Storage<T extends Product> {
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
            catch (InterruptedException ignored) {}
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
            catch (InterruptedException ignored) {}
        }

        items.add(item);
        notify();
    }
}
