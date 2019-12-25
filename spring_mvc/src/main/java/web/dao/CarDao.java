package web.dao;

import web.model.Car;

import java.util.List;

public interface CarDao {
    public void add(Car car);
    public List<Car>getAllCars();
}
