package ru.nsu.ccfit.lopatkin.lab4.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;

import ru.nsu.ccfit.lopatkin.lab4.products.CarPart;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;
import ru.nsu.ccfit.lopatkin.lab4.utils.HibernateSessionFactoryUtil;

import java.lang.reflect.InvocationTargetException;


@Repository
public class ProductDAOImpl<T extends Product & CarPart> implements ProductDAO<T>{
    @Override
    public T findById(long id, Class<T> productType) {
        T t = null;
        try {
            t = productType.getDeclaredConstructor().newInstance();
            return HibernateSessionFactoryUtil.getSessionFactory().openSession().get((Class<T>)t.getClass(), id);
        } catch (Exception e) {
           return null;
        }
    }

    @Override
    public void produceProduct(T product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        product.setProductID((long)session.save(product));
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteProduct(T product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(product);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateUsedCar(T product, Car car, Class<T> productType) {
        T foundProduct = findById(product.getProductID(), productType);
        foundProduct.setCar(car);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(foundProduct);
        tx1.commit();
        session.close();
    }

}
