package ru.nsu.ccfit.lopatkin.lab4.products;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    abstract String getFullVin();

}
