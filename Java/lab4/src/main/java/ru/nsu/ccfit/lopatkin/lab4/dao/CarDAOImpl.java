package ru.nsu.ccfit.lopatkin.lab4.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;

import java.util.List;


@Repository
public class CarDAOImpl implements CarDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public synchronized Car findCarByID(long id) {
        return sessionFactory.openSession().get(Car.class, id);
    }

    @Override
    public void produceCar(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        car.setProductID((long)session.save(car));
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteCar(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(car);
        tx1.commit();
        session.close();
    }

    public List<Car> findAll() {
        List<Car> cars = (List<Car>)sessionFactory.openSession().createQuery("From Car").list();
        return cars;
    }

}
