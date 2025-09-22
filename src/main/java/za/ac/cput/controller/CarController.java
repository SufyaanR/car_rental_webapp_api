package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Car;
import za.ac.cput.service.BusinessUserServiceImpl;
import za.ac.cput.service.CarServiceImpl;
import za.ac.cput.service.ProUserServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "http://localhost:5173")
public class CarController {

   private final CarServiceImpl carService;
   private final BusinessUserServiceImpl businessUserService;
   private final ProUserServiceImpl proUserService;

   @Autowired
   public CarController(CarServiceImpl carService,
                        BusinessUserServiceImpl businessUserService,
                        ProUserServiceImpl proUserService) {
       this.carService = carService;
       this.businessUserService = businessUserService;
       this.proUserService = proUserService;
   }

   @PostMapping
   public Car save(@RequestBody Car car){
        return carService.save(car);
   }

   @GetMapping("/{id}")
   public Car findById(@PathVariable Long id) {
       return carService.findById(id);
   }

   @GetMapping
   public List<Car> findAll() {
       return carService.findAll();
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteById(@PathVariable Long id) {
       carService.deleteById(id);
   }

   @PatchMapping("/{id}")
   public Car updateCar(@PathVariable Long id, @RequestBody Car carUpdates) {
       return carService.update(id, carUpdates);
   }

   @PostMapping("/{id}/availability")
   public Car updateAvailability(@PathVariable Long id, @RequestBody boolean isAvailable) {
       carService.updateAvailability(id, isAvailable);
       return carService.findById(id);
   }
}
