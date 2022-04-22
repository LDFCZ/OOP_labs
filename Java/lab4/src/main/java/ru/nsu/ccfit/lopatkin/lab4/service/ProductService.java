package ru.nsu.ccfit.lopatkin.lab4.service;

import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.lab4.dao.ProductDAO;
import ru.nsu.ccfit.lopatkin.lab4.dao.ProductDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarPart;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;

@Service
public class ProductService<T extends Product & CarPart> {

    private final ProductDAO<T> productDAO = new ProductDAOImpl<>();

    public T findByID(long id, Class<T> productType) {
        return productDAO.findById(id, productType);
    }

    public void produceProduct(T product) {
        product.setProductID(productDAO.produceProduct(product));
    }

    public void deleteProduct(T product) {
        productDAO.deleteProduct(product);
    }

    public void updateUsedCar(T product, Car car, Class<T> productType ) {
        T updatedProduct = productDAO.findById(product.getProductID(), productType);
        updatedProduct.setCar(car);
        productDAO.updateUsedCar(product);
    }
}
