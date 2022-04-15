package ru.nsu.ccfit.lopatkin.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;
import ru.nsu.ccfit.lopatkin.lab4.repos.ProductRepository;

import java.util.Optional;

@Service
@ComponentScan(basePackages = "ru.nsu.ccfit.lopatkin.lab4")
public abstract class ProductServiceAbstract<E extends Product, R extends ProductRepository<E>> {

    @Autowired
    protected R productRepository;

    //private final ProductDAO<T> productDAO = new ProductDAOImpl<>();

    public Optional<E> findByID(long id) {
        return productRepository.findById(id);
    }

    public void produceProduct(E product) {
        productRepository.save(product);
    }

    public void deleteProduct(E product) {
        productRepository.delete(product);
    }

    public void update(E product) {
        productRepository.save(product);
    }
}
