package web.dao;

import org.springframework.stereotype.Component;
import web.model.Car;
import java.util.List;

@Component
public interface CarDao {
    void saveCar(Car car);

    List<Car> getListCar();

    List<Car> getListCarLimit(int limit);

    Car getCarById(int id);
}