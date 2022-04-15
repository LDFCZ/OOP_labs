package ru.nsu.ccfit.lopatkin.lab4.products;

import javax.persistence.*;

@Entity
@Table(name = "produced_cars")
public class Car extends Product{

    @Column (name = "vin")
    private String vin;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private CarBody carBody;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Engine engine;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Accessories accessories;


    public Car() {

    }

    private void generateVin() {
        this.vin = carBody.getFullVin() + engine.getFullVin() + accessories.getFullVin();
    }

    public Car(CarBody carBody, Engine engine, Accessories accessories) {
        this.carBody = carBody;
        this.engine = engine;
        this.accessories = accessories;
        generateVin();
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    @Override
    public  String toString() {
        return vin;
    }

    @Override
    public String getFullVin() {
        return vin + getId();
    }

}
