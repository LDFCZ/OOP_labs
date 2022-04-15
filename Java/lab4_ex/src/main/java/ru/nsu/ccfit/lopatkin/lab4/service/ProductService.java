package ru.nsu.ccfit.lopatkin.lab4.service;

import ru.nsu.ccfit.lopatkin.lab4.products.Product;

import java.util.Optional;

public interface ProductService <E extends Product>{
    Optional<E> findByID(long id);

    void produceProduct(E product);

    void deleteProduct(E product);

    void update(E product);
}
