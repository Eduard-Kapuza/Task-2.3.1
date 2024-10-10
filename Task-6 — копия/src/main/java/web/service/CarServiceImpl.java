package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CarDao;
import web.model.Car;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public void saveCar(Car car) {
        carDao.saveCar(car);
    }

    @Override
    public int showAmountCars() {
        return getListCar().size();
    }

    @Override
    public List<Car> getListCar() {
        return carDao.getListCar();
    }

    @Override
    public Car getCarById(int id) {
        return carDao.getCarById(id);
    }

    @Override
    public List<Car> getListCarLimit(int limit) {
        if (limit >= 5 || limit == 0) {
            limit = carDao.getListCar().size();
        }
        return carDao.getListCarLimit(limit);
    }
}