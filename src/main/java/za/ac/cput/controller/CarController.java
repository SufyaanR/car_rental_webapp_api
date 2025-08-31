package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.ProUser;
import za.ac.cput.service.BusinessUserServiceImpl;
import za.ac.cput.service.CarServiceImpl;
import za.ac.cput.service.ProUserServiceImpl;
import java.io.IOException;
import java.math.BigDecimal;
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
   public ResponseEntity<List<Car>> findAll() {
       return ResponseEntity.ok(carService.findAll());
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable Long id) {
       carService.deleteById(id);
       return ResponseEntity.noContent().build();
   }

   @PatchMapping("/{id}")
   public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carUpdates) {
       Car updatedCar = carService.update(id, carUpdates);
       return ResponseEntity.ok(updatedCar);
   }

   @PostMapping("/{id}/availability")
   public ResponseEntity<Car> updateAvailability(@PathVariable Long id, @RequestParam boolean isAvailable) {
       carService.updateAvailability(id, isAvailable);
       return ResponseEntity.ok(carService.findById(id));
   }
}
