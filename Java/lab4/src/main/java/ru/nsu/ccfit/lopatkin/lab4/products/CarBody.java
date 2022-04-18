package ru.nsu.ccfit.lopatkin.lab4.products;

import ru.nsu.ccfit.lopatkin.lab4.products.ConstSpace.ConstSpace;

import javax.persistence.*;

@Entity
@Table(name = "car_bodies")
public class CarBody extends Product implements CarPart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "vin")
    private String vin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;


    public Car getCar() {
        return car;
    }

    @Override
    public void setCar(Car car) {
        this.car = car;
    }

    public CarBody() {
        vin = ConstSpace.CAR_BODY_VIN;
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
        return vin + getProductID();
    }
}
