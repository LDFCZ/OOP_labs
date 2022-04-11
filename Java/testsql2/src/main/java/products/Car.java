package products;

import javax.persistence.*;

@Entity
@Table(name = "produced_cars")
public class Car extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public void generateVin(long carBodyID, long engineID, long accessoriesID) {
        this.vin = carBody.getVin() + carBodyID + engine.getVin() + engineID + accessories.getVin() + accessoriesID;
    }

    public Car(CarBody carBody, Engine engine, Accessories accessories) {
        this.carBody = carBody;
        this.carBody.setCar(this);
        this.engine = engine;
        this.engine.setCar(this);
        this.accessories = accessories;
        this.accessories.setCar(this);
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
