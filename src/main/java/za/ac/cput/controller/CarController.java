package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Car;
import za.ac.cput.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private CarService service;

    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Car create(@RequestBody Car car) {
        return service.create(car);
    }

    @GetMapping("/read/{carId}")
    public Car read(@PathVariable Long carId) {
        return service.read(carId);
    }

    @PutMapping("/update")
    public Car update(@RequestBody Car car) {
        return service.update(car);
    }

    @DeleteMapping("/delete/{carId}")
    public boolean delete(@PathVariable Long carId) {
        return service.delete(carId);
    }

    @GetMapping("/getAll")
    public List<Car> getAll() {
        return service.getAll();
    }
}
