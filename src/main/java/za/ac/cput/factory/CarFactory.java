package za.ac.cput.factory;

import za.ac.cput.domain.Car;

import java.math.BigDecimal;

public class CarFactory {
    public static Car createCar(byte[] image, String brand, String model, String type, BigDecimal pricePerDay, int seatCapacity,
                                float bootCapacity, float engineCapacity, String transmission, String description,
                                String collectionLocation, boolean isAvailable) {

        return new Car.Builder()
                .setImage(image)
                .setBrand(brand)
                .setModel(model)
                .setType(type)
                .setPricePerDay(pricePerDay)
                .setSeatCapacity(seatCapacity)
                .setBootCapacity(bootCapacity)
                .setEngineCapacity(engineCapacity)
                .setTransmission(transmission)
                .setDescription(description)
                .setCollectionLocation(collectionLocation)
                .setAvailable(isAvailable)
                .build();
    }
}
