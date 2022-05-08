package ru.nsu.ccfit.lopatkin.client.configurations;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.nsu.ccfit.lopatkin.client.factories.PostRequestFactory;

@Configuration
@ComponentScan (basePackages = {"ru.nsu.ccfit.lopatkin.client"})
public class PostFactoryConfiguration {

    @Bean
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(PostRequestFactory.class);
        return factoryBean;
    }


}
