package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import products.Accessories;
import products.Car;
import products.CarBody;
import products.Engine;
import utils.HibernateSessionFactoryUtil;

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
