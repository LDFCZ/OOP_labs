package ru.nsu.ccfit.lopatkin.lab4.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.nsu.ccfit.lopatkin.lab4.dao.CarDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.dao.ProductDAOImpl;
import ru.nsu.ccfit.lopatkin.lab4.products.*;

@Configuration
public class SessionFactoryConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Accessories.class);
        configuration.addAnnotatedClass(CarBody.class);
        configuration.addAnnotatedClass(Engine.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    @Bean
    public CarDAOImpl carDAO() {
        return new CarDAOImpl();
    }

    @Bean
    public <T extends Product & CarPart> ProductDAOImpl<T> productDAO() {
        return new ProductDAOImpl<T>();
    }
}
