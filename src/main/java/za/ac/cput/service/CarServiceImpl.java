package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Car;
import za.ac.cput.repository.CarRepo;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements ICarService {

    private final CarRepo carRepo;

    @Autowired
    public CarServiceImpl(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @Override
    public Car save(Car car) {
        if ((car.getBusinessUser() == null && car.getProUser() == null) ||
                (car.getBusinessUser() != null && car.getProUser() != null)) {
            throw new IllegalStateException("Car must have exactly one owner: either BusinessUser or ProUser");
        }

        if (car.getIsAvailable() == null) {
            car.updateAvailability(true);
        }

        return carRepo.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    @Override
    public List<Car> findAll() {
        return carRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        carRepo.deleteById(id);
    }

    public Car update(Long carId, Car carUpdates) {
        Car existingCar = findById(carId);

        Car updatedCar = new Car.Builder()
                .setCarId(existingCar.getCarId())
                .setBrand(carUpdates.getBrand() != null ? carUpdates.getBrand() : existingCar.getBrand())
                .setModel(carUpdates.getModel() != null ? carUpdates.getModel() : existingCar.getModel())
                .setType(carUpdates.getType() != null ? carUpdates.getType() : existingCar.getType())
                .setPricePerDay(carUpdates.getPricePerDay() != null ? carUpdates.getPricePerDay() : existingCar.getPricePerDay())
                .setSeatCapacity(carUpdates.getSeatCapacity() != 0 ? carUpdates.getSeatCapacity() : existingCar.getSeatCapacity())
                .setBootCapacity(carUpdates.getBootCapacity() != 0 ? carUpdates.getBootCapacity() : existingCar.getBootCapacity())
                .setEngineCapacity(carUpdates.getEngineCapacity() != 0 ? carUpdates.getEngineCapacity() : existingCar.getEngineCapacity())
                .setTransmission(carUpdates.getTransmission() != null ? carUpdates.getTransmission() : existingCar.getTransmission())
                .setDescription(carUpdates.getDescription() != null ? carUpdates.getDescription() : existingCar.getDescription())
                .setCollectionLocation(carUpdates.getCollectionLocation() != null ? carUpdates.getCollectionLocation() : existingCar.getCollectionLocation())
                .setIsAvailable(carUpdates.getIsAvailable() != null ? carUpdates.getIsAvailable() : existingCar.getIsAvailable())
                .setImage(carUpdates.getImage() != null ? carUpdates.getImage() : existingCar.getImage())
                .setBusinessUser(existingCar.getBusinessUser())
                .setProUser(existingCar.getProUser())
                .build();

        return carRepo.save(updatedCar);
    }

    @Override
    public void updateAvailability(Long carId, boolean isAvailable) {
        Car car = findById(carId);
        car.updateAvailability(isAvailable);
        carRepo.save(car);
    }
}
