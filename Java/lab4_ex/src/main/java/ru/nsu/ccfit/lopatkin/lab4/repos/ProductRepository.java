package ru.nsu.ccfit.lopatkin.lab4.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import ru.nsu.ccfit.lopatkin.lab4.products.Product;

@NoRepositoryBean
public interface ProductRepository<E extends Product> extends CrudRepository<E, Long> {
}
