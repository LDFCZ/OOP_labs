package ru.nsu.ccfit.lopatkin.lab4.products;

public abstract class Product {
    private final long id;

    public Product(long productId) {
        id = productId;
    }

    public long getID() {
        return id;
    }
}
