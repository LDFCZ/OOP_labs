package ru.nsu.ccfit.lopatkin.lab4.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.utils.HibernateSessionFactoryUtil;

import java.util.List;


@Repository
public class CarDAOImpl implements CarDAO {

    private static final String FROM_CAR = "From Car";

    @Override
    public synchronized Car findCarByID(long id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Car.class, id);
        }
    }

    @Override
    public synchronized void produceCar(Car car) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            car.setProductID((long)session.save(car));
            tx1.commit();
        }
    }

    @Override
    public void deleteCar(Car car) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.delete(car);
            tx1.commit();
        }
    }

    public List<Car> findAll() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            List<Car> cars = session.createQuery(FROM_CAR).list();
            return cars;
        }
    }

}
