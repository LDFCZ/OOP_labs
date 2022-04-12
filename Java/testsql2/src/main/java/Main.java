import products.Accessories;
import products.Car;
import products.CarBody;
import products.Engine;
import services.CarService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        CarService carService = new CarService();
        Engine e = new Engine();
        CarBody cb = new CarBody();
        Accessories a = new Accessories();

        Car c = new Car(cb, e, a);

        carService.producedCar(c, cb, e, a);

    }
}
