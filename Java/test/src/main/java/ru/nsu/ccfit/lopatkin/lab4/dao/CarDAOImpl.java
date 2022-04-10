package ru.nsu.ccfit.lopatkin.lab4.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class CarDAOImpl implements CarDAO {
    @Override
    public Car findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Car.class, id);
    }

    @Override
    public void producedCar(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(car);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Car car) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(car);
        tx1.commit();
        session.close();
    }
}
