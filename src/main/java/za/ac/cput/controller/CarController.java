package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.ProUser;
import za.ac.cput.service.CarServiceImpl;
import za.ac.cput.service.ProUserServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

//    private final CarServiceImpl carService;
//    private final BusinessUserServiceImpl businessUserService;
//    private final ProUserServiceImpl proUserService;
//
//    @Autowired
//    public CarController(CarServiceImpl carService,
//                         BusinessUserServiceImpl businessUserService,
//                         ProUserServiceImpl proUserService) {
//        this.carService = carService;
//        this.businessUserService = businessUserService;
//        this.proUserService = proUserService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Car> save(
//            @RequestParam String brand,
//            @RequestParam String model,
//            @RequestParam String type,
//            @RequestParam BigDecimal pricePerDay,
//            @RequestParam int seatCapacity,
//            @RequestParam float bootCapacity,
//            @RequestParam float engineCapacity,
//            @RequestParam String transmission,
//            @RequestParam String description,
//            @RequestParam String collectionLocation,
//            @RequestParam boolean isAvailable,
//            @RequestParam(required = false) MultipartFile image,
//            @RequestParam(required = false) Long businessUserId,
//            @RequestParam(required = false) Long proUserId
//    ) throws IOException {
//
//        if ((businessUserId == null && proUserId == null) || (businessUserId != null && proUserId != null)) {
//            throw new IllegalStateException("Car must have exactly one owner: either BusinessUser or ProUser");
//        }
//
//        BusinessUser businessUser = null;
//        ProUser proUser = null;
//
//        if (businessUserId != null) {
//            businessUser = businessUserService.findById(businessUserId);
//        }
//
//        if (proUserId != null) {
//            proUser = proUserService.findById(proUserId);
//        }
//
//        Car.Builder builder = new Car.Builder()
//                .setBrand(brand)
//                .setModel(model)
//                .setType(type)
//                .setPricePerDay(pricePerDay)
//                .setSeatCapacity(seatCapacity)
//                .setBootCapacity(bootCapacity)
//                .setEngineCapacity(engineCapacity)
//                .setTransmission(transmission)
//                .setDescription(description)
//                .setCollectionLocation(collectionLocation)
//                .setIsAvailable(isAvailable)
//                .setBusinessUser(businessUser)
//                .setProUser(proUser);
//
//        if (image != null && !image.isEmpty()) {
//            builder.setImage(image.getBytes());
//        }
//
//        Car car = builder.build();
//        Car savedCar = carService.save(car);
//        return ResponseEntity.ok(savedCar);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Car> findById(@PathVariable Long id) {
//        return ResponseEntity.ok(carService.findById(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Car>> findAll() {
//        return ResponseEntity.ok(carService.findAll());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
//        carService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carUpdates) {
//        Car updatedCar = carService.update(id, carUpdates);
//        return ResponseEntity.ok(updatedCar);
//    }
//
//    @PostMapping("/{id}/availability")
//    public ResponseEntity<Car> updateAvailability(@PathVariable Long id, @RequestParam boolean isAvailable) {
//        carService.updateAvailability(id, isAvailable);
//        return ResponseEntity.ok(carService.findById(id));
//    }
}
