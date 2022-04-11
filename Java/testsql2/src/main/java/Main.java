import models.Auto;
import models.User;
import products.Accessories;
import products.Car;
import products.CarBody;
import products.Engine;
import services.CarService;
import services.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        //UserService userService = new UserService();
        //User user = new User("Masha",26);
        //userService.saveUser(user);
        //Auto ferrari = new Auto("Ferrari", "red");
        //ferrari.setUser(user);
        //user.addAuto(ferrari);
        //Auto ford = new Auto("Ford", "black");
        //ford.setUser(user);
        //user.addAuto(ford);
        //userService.updateUser(user);

        CarService carService = new CarService();
        Engine e = new Engine();
        CarBody cb = new CarBody();
        Accessories a = new Accessories();

        Car c = new Car(cb, e, a);

        carService.producedCar(c, cb, e, a);

    }
}
