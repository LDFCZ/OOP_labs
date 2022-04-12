package ru.nsu.ccfit.lopatkin.lab4.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.nsu.ccfit.lopatkin.lab4.products.Accessories;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;
import ru.nsu.ccfit.lopatkin.lab4.products.CarBody;
import ru.nsu.ccfit.lopatkin.lab4.products.Engine;
import ru.nsu.ccfit.lopatkin.lab4.utils.HibernateSessionFactoryUtil;

public class CarDAOImpl implements CarDAO {
    @Override
    public Car findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Car.class, id);
    }

    @Override
    public void producedCar(Car car, CarBody carBody, Engine engine, Accessories accessories) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        long carBodyID = (long) session.save(carBody);
        long engineID = (long)session.save(engine);
        long accessoriesID = (long)session.save(accessories);
        car.generateVin(carBodyID, engineID, accessoriesID);
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
