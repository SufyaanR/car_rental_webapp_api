package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Car;
import za.ac.cput.repository.CarRepository;

import java.util.List;

@Service
public class CarService implements ICarService {

    private ICarService service;
    @Autowired
    private CarRepository repository;

    @Override
    public Car create(Car car) {
        return this.repository.save(car);
    }

    @Override
    public Car read(Long aLong) {
        return this.repository.findById(aLong).orElse(null);
    }

    @Override
    public Car update(Car car) {
        return this.repository.save(car);
    }

    @Override
    public boolean delete(Long aLong) {
        this.repository.deleteById(aLong);
        return true;
    }

    @Override
    public List<Car> getAll() {
        return this.repository.findAll();
    }

}
