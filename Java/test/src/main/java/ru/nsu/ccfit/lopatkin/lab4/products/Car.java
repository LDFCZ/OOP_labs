package ru.nsu.ccfit.lopatkin.lab4.products;

import javax.persistence.*;

@Entity
@Table(name = "produced_cars")
public class Car extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "vin")
    private String vin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_body_id")
    private CarBody carBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessories_id")
    private Accessories accessories;

    public Car() {

    }

    public Car(CarBody carBody, Engine engine, Accessories accessories) {
        this.carBody = carBody;
        this.engine = engine;
        this.accessories = accessories;
        this.vin = carBody.getVin() + carBody.getId() + engine.getVin()+engine.getId()+id;
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
        return vin;
    }
}
