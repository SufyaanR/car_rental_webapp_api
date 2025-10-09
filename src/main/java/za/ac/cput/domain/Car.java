package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
/* Car.java
     Car POJO class
     Author: S Rawoot (221075127)
     Date: 02 August 2025 */

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Lob
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
    private Boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "pro_user_id", nullable = true)
    @JsonBackReference("car-proUser")
    private ProUser proUser;

    @ManyToOne
    @JoinColumn(name = "business_user_id", nullable = true)
    @JsonBackReference("car-businessUser")
    private BusinessUser businessUser;

    protected Car() {}

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
        this.proUser = builder.proUser;
        this.businessUser = builder.businessUser;
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

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public ProUser getProUser() {
        return proUser;
    }

    public BusinessUser getBusinessUser() {
        return businessUser;
    }

    public void updateAvailability(Boolean isAvailable) {
        if (isAvailable != null) {
            this.isAvailable = isAvailable;
        }
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
        private Boolean isAvailable = true;
        private ProUser proUser;
        private BusinessUser businessUser;

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

        public Builder setIsAvailable(Boolean isAvailable) {
            this.isAvailable = (isAvailable != null ? isAvailable : true);
            return this;
        }

        public Builder setProUser(ProUser proUser) {
            this.proUser = proUser;
            return this;
        }

        public Builder setBusinessUser(BusinessUser businessUser) {
            this.businessUser = businessUser;
            return this;
        }

        public Car build() {
            if ((proUser == null && businessUser == null) || (proUser != null && businessUser != null)) {
                throw new IllegalStateException("Car must have exactly one owner: either ProUser or BusinessUser");
            }
            if (isAvailable == null) isAvailable = true;
            return new Car(this);
        }
    }
}
