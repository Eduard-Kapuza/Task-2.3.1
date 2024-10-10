package web.dao;

import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.Car;
import java.util.List;


@Repository
@EnableTransactionManagement
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Car> getListCarLimit(int limit) {

        String queryHQL = "from Car ORDER BY id ASC LIMIT ?1";
        Query q = sessionFactory.getCurrentSession().createQuery(queryHQL, Car.class);
        q.setParameter(1, limit);


        List<Car> cars = q.getResultList();
        return cars;
    }

    @Override
    @Transactional
    public Car getCarById(int id) {
        String queryHQL = "from Car where id=?1";
        Query q = sessionFactory.getCurrentSession().createQuery(queryHQL, Car.class);
        q.setParameter(1, id);
        return (Car) q.getResultList().stream().findFirst().orElse(new Car());
    }

    @Override
    @Transactional
    public List<Car> getListCar() {
        String queryHQL = "from Car";
        List<Car> cars = sessionFactory.getCurrentSession()
                .createQuery(queryHQL, Car.class)
                .getResultList();
        return cars;
    }

    @Override
    @Transactional
    public void saveCar(Car car) {
        sessionFactory.getCurrentSession().persist(car);
    }
}