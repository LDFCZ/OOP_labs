package ru.nsu.ccfit.lopatkin.lab4.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.lab4.dao.ProductDAO;
import ru.nsu.ccfit.lopatkin.lab4.dao.ProductDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarPart;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;

@Service
@ComponentScan(basePackages = "ru.nsu.ccfit.lopatkin.lab4")
public class ProductService<T extends Product & CarPart> {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProductService.class);

    //private final ProductDAO<T> productDAO = new ProductDAOImpl<>();

    public T findByID(long id, Class<T> productType) {
        return (T) context.getBean(ProductDAOImpl.class).findById(id, productType);
    }

    public void produceProduct(T product) {
        context.getBean(ProductDAOImpl.class).produceProduct(product);
    }

    public void deleteProduct(T product) {
        context.getBean(ProductDAOImpl.class).deleteProduct(product);
    }

    public void updateUsedCar(T product, Car car, Class<T> productType ) {
        context.getBean(ProductDAOImpl.class).updateUsedCar(product, car, productType);
    }
}
