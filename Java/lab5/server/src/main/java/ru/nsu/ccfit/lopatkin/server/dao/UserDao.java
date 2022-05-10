package ru.nsu.ccfit.lopatkin.server.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.nsu.ccfit.lopatkin.server.models.User;
import ru.nsu.ccfit.lopatkin.server.utils.HibernateFactory;


public class UserDao {
    //new user

    //find by name

    //new user
    public long save(User user) {
        long id = -1;
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            id = (long) session.save(user);
            tx1.commit();
        }
        return id;
    }

    public User findByName(String name) {
        User user = null;
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User u where u.name = :name", User.class);
            query.setParameter("name", name);
            user = query.getSingleResult();
        }
        return user;
    }
}
