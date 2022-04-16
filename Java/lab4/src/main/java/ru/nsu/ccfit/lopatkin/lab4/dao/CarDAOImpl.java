package ru.nsu.ccfit.lopatkin.lab4.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.utils.HibernateSessionFactoryUtil;

import java.util.List;


@Repository
public class CarDAOImpl implements CarDAO {

    public static final String FROM_CAR = "From Car";

    @Override
    public synchronized Car findCarByID(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Car.class, id);
    }

    @Override
    public synchronized void produceCar(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        car.setProductID((long)session.save(car));
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteCar(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(car);
        tx1.commit();
        session.close();
    }

    public List<Car> findAll() {
        List<Car> cars = (List<Car>)HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(FROM_CAR).list();
        return cars;
    }

}
