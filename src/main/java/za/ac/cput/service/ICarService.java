package za.ac.cput.service;

import za.ac.cput.domain.Car;

public interface ICarService extends IService<Car, Long>{
    void updateAvailability(Long carId, boolean isAvailable);
}
