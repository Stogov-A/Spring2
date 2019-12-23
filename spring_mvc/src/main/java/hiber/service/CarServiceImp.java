package hiber.service;

import hiber.dao.CarDaoImp;
import hiber.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImp implements CarService {
    @Autowired
    CarDaoImp carDaoImp;

    @Transactional
    @Override
    public List<Car> getFiveCars() {
        List<Car> cars = carDaoImp.getAllCars();
        if (cars.size() < 5) {
            for (int i = 0; i < 5; i++) {
                carDaoImp.add(new Car("brand = " + i, "model = " + i, "number = " + i));
            }
            cars = carDaoImp.getAllCars();
        }
        return cars;
    }
}
