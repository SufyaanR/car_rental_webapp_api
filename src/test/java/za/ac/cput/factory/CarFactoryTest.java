package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.BusinessUser;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.ProUser;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class CarFactoryTest {

//    @Test
//    void testCreateCarBU() {
//        BusinessUser businessUser = new BusinessUser.Builder()
//                .setFirstName("Alice")
//                .setLastName("Smith")
//                .setBusinessName("Alice Rentals")
//                .setBusinessRegistrationNumber("BR123")
//                .setUsername("alice123")
//                .setPassword("pass")
//                .setEmail("alice@email.com")
//                .setPhoneNumber("0123456789")
//                .setUserType(UserType.BUSINESS)
//                .setLogin(true)
//                .setBankName("Bank")
//                .setAccountHolder("Alice Smith")
//                .setAccountNumber(12345)
//                .setAccountType("Checking")
//                .build();
//
//        Car car = CarFactory.create(
//                new byte[]{1, 2, 3},
//                "Toyota",
//                "Corolla",
//                "Sedan",
//                BigDecimal.valueOf(500),
//                5,
//                400,
//                1.6f,
//                "Automatic",
//                "Reliable car",
//                "Cape Town",
//                true,
//                businessUser,
//                null
//        );
//
//        assertNotNull(car);
//        assertEquals("Toyota", car.getBrand());
//        assertEquals("Corolla", car.getModel());
//        assertEquals("Sedan", car.getType());
//        assertEquals(BigDecimal.valueOf(500), car.getPricePerDay());
//        assertEquals(5, car.getSeatCapacity());
//        assertEquals(400f, car.getBootCapacity());
//        assertEquals(1.6f, car.getEngineCapacity());
//        assertEquals("Automatic", car.getTransmission());
//        assertEquals("Reliable car", car.getDescription());
//        assertEquals("Cape Town", car.getCollectionLocation());
//        assertTrue(car.getIsAvailable());
//        assertNotNull(car.getBusinessUser());
//        assertNull(car.getProUser());
//    }
//
//    @Test
//    void testCreateCarWithPU() {
//        ProUser proUser = new ProUser.Builder()
//                .setFirstName("Bob")
//                .setLastName("Jones")
//                .setUsername("bob123")
//                .setPassword("pass")
//                .setEmail("bob@email.com")
//                .setPhoneNumber("0987654321")
//                .setUserType(UserType.PRO)
//                .setLogin(true)
//                .setBankName("Bank")
//                .setAccountHolder("Bob Jones")
//                .setAccountNumber(67890)
//                .setAccountType("Savings")
//                .build();
//
//        Car car = CarFactory.create(
//                new byte[]{4, 5, 6},
//                "Honda",
//                "Civic",
//                "Sedan",
//                BigDecimal.valueOf(600),
//                5,
//                350,
//                1.8f,
//                "Manual",
//                "Fuel efficient",
//                "Johannesburg",
//                true,
//                null,
//                proUser
//        );
//
//        assertNotNull(car);
//        assertEquals("Honda", car.getBrand());
//        assertEquals("Civic", car.getModel());
//        assertEquals("Sedan", car.getType());
//        assertEquals(BigDecimal.valueOf(600), car.getPricePerDay());
//        assertEquals(5, car.getSeatCapacity());
//        assertEquals(350f, car.getBootCapacity());
//        assertEquals(1.8f, car.getEngineCapacity());
//        assertEquals("Manual", car.getTransmission());
//        assertEquals("Fuel efficient", car.getDescription());
//        assertEquals("Johannesburg", car.getCollectionLocation());
//        assertTrue(car.getIsAvailable());
//        assertNull(car.getBusinessUser());
//        assertNotNull(car.getProUser());
//    }
}
