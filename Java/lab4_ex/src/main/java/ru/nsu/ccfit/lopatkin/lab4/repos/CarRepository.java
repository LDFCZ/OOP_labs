package ru.nsu.ccfit.lopatkin.lab4.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.lopatkin.lab4.products.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}
