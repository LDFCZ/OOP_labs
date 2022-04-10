package ru.nsu.ccfit.lopatkin.lab4.products;

import javax.persistence.*;

@Entity
@Table(name = "engines")
public class Engine extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "vin")
    private String vin;

    public Engine() {
        vin = "200TFSI";
    }

    public Long getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFullVin() {
        return vin + id;
    }
}
