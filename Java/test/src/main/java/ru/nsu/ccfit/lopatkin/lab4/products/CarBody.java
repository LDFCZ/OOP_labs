package ru.nsu.ccfit.lopatkin.lab4.products;

import javax.persistence.*;

@Entity
@Table(name = "car_bodies")
public class CarBody extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "vin")
    private String vin;

    public CarBody() {
        vin = "MSDN";
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

    @Override
    public String getFullVin() {
        return vin + id;
    }
}
