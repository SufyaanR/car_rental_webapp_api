package za.ac.cput.domain;
import jakarta.persistence.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.List;

/* Car.java
     Car POJO class
     Author: S Rawoot (221075127)
     Date: 02 August 2025 */
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private byte[] image;
    private String brand;
    private String model;
    private String type;
    private BigDecimal pricePerDay;
    private int seatCapacity;
    private float bootCapacity;
    private float engineCapacity;
    private String transmission;
    private String description;
    private String collectionLocation;
    private boolean isAvailable;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId")
//    private RentalProvider rentalProvider;

    protected Car(){

    }

    private Car(Builder builder) {
        this.carId = builder.carId;
        this.image = builder.image;
        this.brand = builder.brand;
        this.model = builder.model;
        this.type = builder.type;
        this.pricePerDay = builder.pricePerDay;
        this.seatCapacity = builder.seatCapacity;
        this.bootCapacity = builder.bootCapacity;
        this.engineCapacity = builder.engineCapacity;
        this.transmission = builder.transmission;
        this.description = builder.description;
        this.collectionLocation = builder.collectionLocation;
        this.isAvailable = builder.isAvailable;
}

    public Long getCarId() {
        return carId;
    }

    public byte[] getImage() {
        return image;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public float getBootCapacity() {
        return bootCapacity;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getDescription() {
        return description;
    }

    public String getCollectionLocation() {
        return collectionLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", image=" + (image != null ? "Image data" : "No image") +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", seatCapacity=" + seatCapacity +
                ", bootCapacity=" + bootCapacity +
                ", engineCapacity=" + engineCapacity +
                ", transmission='" + transmission + '\'' +
                ", description='" + description + '\'' +
                ", collectionLocation='" + collectionLocation + '\'' +
                ", isAvailable=" + isAvailable +
                "}";
    }

    public static class Builder {
        private Long carId;
        private byte[] image;
        private String brand;
        private String model;
        private String type;
        private BigDecimal pricePerDay;
        private int seatCapacity;
        private float bootCapacity;
        private float engineCapacity;
        private String transmission;
        private String description;
        private String collectionLocation;
        private boolean isAvailable;

        public Builder setCarId(Long carId) {
            this.carId = carId;
            return this;
        }

        public Builder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setPricePerDay(BigDecimal pricePerDay) {
            this.pricePerDay = pricePerDay;
            return this;
        }

        public Builder setSeatCapacity(int seatCapacity) {
            this.seatCapacity = seatCapacity;
            return this;
        }

        public Builder setBootCapacity(float bootCapacity) {
            this.bootCapacity = bootCapacity;
            return this;
        }

        public Builder setEngineCapacity(float engineCapacity) {
            this.engineCapacity = engineCapacity;
            return this;
        }

        public Builder setTransmission(String transmission) {
            this.transmission = transmission;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCollectionLocation(String collectionLocation) {
            this.collectionLocation = collectionLocation;
            return this;
        }

        public Builder setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

    public Builder copy(Car car) {
            this.carId = car.carId;
            this.image = car.image;
            this.brand = car.brand;
            this.model = car.model;
            this.type = car.type;
            this.pricePerDay = car.pricePerDay;
            this.seatCapacity = car.seatCapacity;
            this.bootCapacity = car.bootCapacity;
            this.engineCapacity = car.engineCapacity;
            this.transmission = car.transmission;
            this.description = car.description;
            this.collectionLocation = car.collectionLocation;
            this.isAvailable = car.isAvailable;
            return this;
    }

    public Car build() {
        return new Car(this);
    }

    }

}
