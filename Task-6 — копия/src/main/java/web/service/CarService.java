package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;
import java.util.List;

@Component
public interface CarService {

    void saveCar(Car car);

    List<Car> getListCar();

    int showAmountCars();

    List<Car> getListCarLimit(int limit);

    Car getCarById(int id);
}