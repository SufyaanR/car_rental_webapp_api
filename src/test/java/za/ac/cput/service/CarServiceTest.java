package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.UserType;
import za.ac.cput.factory.CarFactory;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CarServiceTest {

//    @Autowired
//    private CarServiceImpl carService;
//
//    @Autowired
//    private BusinessUserServiceImpl businessUserService;
//
//    private BusinessUser businessUser;
//    private Car car;
//
//    @BeforeEach
//    void setup() {
//        businessUser = BusinessUserFactory.createBusinessUser(
//                "John",
//                "Doe",
//                java.time.LocalDate.of(1985, 1, 1),
//                "1234567890123",
//                "john@business.com",
//                "0821234567",
//                UserType.BUSINESS,
//                "johndoe",
//                "password",
//                true,
//                "ABSA",
//                "John Doe",
//                123456,
//                "Savings",
//                "JD Motors",
//                "BR123456"
//        );
//
//        businessUser = businessUserService.save(businessUser);
//
//        car = CarFactory.create(
//                new byte[10],
//                "Toyota",
//                "Corolla",
//                "Sedan",
//                BigDecimal.valueOf(500),
//                5,
//                450.5f,
//                1.6f,
//                "Automatic",
//                "Reliable car",
//                "Johannesburg",
//                true,
//                businessUser,
//                null
//        );
//        car = carService.save(car);
//    }
//
//    @Test
//    void testSaveCar() {
//        assertNotNull(car.getCarId());
//    }
//
//    @Test
//    void testFindById() {
//        Car found = carService.findById(car.getCarId());
//        assertEquals(car.getCarId(), found.getCarId());
//        assertEquals(car.getBrand(), found.getBrand());
//    }
//
//    @Test
//    void testFindAll() {
//        List<Car> cars = carService.findAll();
//        assertTrue(cars.size() >= 1);
//    }
//
//    @Test
//    void testUpdateCar() {
//        Car updates = new Car.Builder()
//                .setBrand("Honda")
//                .setModel("Civic")
//                .setPricePerDay(BigDecimal.valueOf(600))
//                .setIsAvailable(false)
//                .setBusinessUser(car.getBusinessUser())
//                .build();
//
//        Car updated = carService.update(car.getCarId(), updates);
//
//        assertEquals("Honda", updated.getBrand());
//        assertEquals("Civic", updated.getModel());
//        assertEquals(BigDecimal.valueOf(600), updated.getPricePerDay());
//        assertFalse(updated.getIsAvailable());
//        assertEquals(car.getSeatCapacity(), updated.getSeatCapacity());
//        assertEquals(car.getCarId(), updated.getCarId());
//        assertEquals(car.getBusinessUser(), updated.getBusinessUser());
//    }
//
//    @Test
//    void testUpdateAvailability() {
//        carService.updateAvailability(car.getCarId(), false);
//        Car updated = carService.findById(car.getCarId());
//        assertFalse(updated.getIsAvailable());
//
//        carService.updateAvailability(car.getCarId(), true);
//        updated = carService.findById(car.getCarId());
//        assertTrue(updated.getIsAvailable());
//    }
//
//    @Test
//    void testDeleteCar() {
//        Long id = car.getCarId();
//        carService.deleteById(id);
//        assertThrows(RuntimeException.class, () -> carService.findById(id));
//    }
}
