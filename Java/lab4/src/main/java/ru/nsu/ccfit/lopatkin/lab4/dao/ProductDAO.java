package ru.nsu.ccfit.lopatkin.lab4.dao;


import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarPart;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;


public interface ProductDAO<T extends Product & CarPart> {
    T findById(long id, Class<T> productType);

    long produceProduct(T product);

    void deleteProduct(T product);

    void updateUsedCar(T product);

}
