package ru.nsu.ccfit.lopatkin.lab4.products;

import javax.persistence.*;

@Entity
@Table(name = "engines")
public class Engine extends Product implements CarPart{

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

    public Engine() {
        vin = "200TFSI";
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String getFullVin() {
        return vin + getId();
    }
}
