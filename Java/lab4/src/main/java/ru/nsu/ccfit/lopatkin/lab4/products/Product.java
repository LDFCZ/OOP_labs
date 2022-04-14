package ru.nsu.ccfit.lopatkin.lab4.products;

public abstract class Product {
    private long id;

    public void setProductID(long id) {
        this.id = id;
    }

    public long getProductID() {
        return id;
    }

    abstract public String getFullVin();

}
