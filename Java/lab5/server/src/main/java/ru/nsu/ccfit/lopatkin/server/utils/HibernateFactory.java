package ru.nsu.ccfit.lopatkin.server.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.nsu.ccfit.lopatkin.server.models.Message;
import ru.nsu.ccfit.lopatkin.server.models.User;

public class HibernateFactory {
    private static SessionFactory sessionFactory;

    private HibernateFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                //configuration.addAnnotatedClass(Message.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);

                // TODO log it
            }
        }
        return sessionFactory;
    }
}
