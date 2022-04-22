package ru.nsu.ccfit.lopatkin.lab4.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarPart;
import ru.nsu.ccfit.lopatkin.lab4.products.Product;
import ru.nsu.ccfit.lopatkin.lab4.utils.HibernateSessionFactoryUtil;


@Repository
public class ProductDAOImpl<T extends Product & CarPart> implements ProductDAO<T>{
    @Override
    public T findById(long id, Class<T> productType) {
        T t = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            t = productType.getDeclaredConstructor().newInstance();
            return session.get((Class<T>)t.getClass(), id);
        } catch (Exception e) {
           return null;
        }
    }

    @Override
    public synchronized long produceProduct(T product) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            long id = (long)session.save(product);
            tx1.commit();
            return id;
        }
    }

    @Override
    public void deleteProduct(T product) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(product);
            tx1.commit();
        }
    }

    @Override
    public synchronized void updateUsedCar(T product) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.update(product);
            tx1.commit();
        }
    }

}
