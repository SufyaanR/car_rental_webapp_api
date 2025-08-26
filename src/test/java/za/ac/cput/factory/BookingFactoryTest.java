package za.ac.cput.factory;

import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.BookingStatus;
import za.ac.cput.domain.Car;
import za.ac.cput.domain.ProUser;
import za.ac.cput.domain.UserType;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BookingFactoryTest {

    @Test
    void testCreateBooking() {
        BasicUser user = BasicUserFactory.create(
                "Ashley",
                "Smith",
                "ashleysmith",
                "password123",
                "ashley@example.com",
                "0123456789",
                LocalDate.of(1990, 5, 20),
                "9005201234087",
                true
        );

        ProUser proUser = new ProUser.Builder()
        .setFirstName("John")
        .setLastName("Doe")
        .setUsername("johndoe")
        .setPassword("password123")
        .setEmail("john@example.com")
        .setPhoneNumber("0123456789")
        .setDateOfBirth(LocalDate.of(1990, 1, 1))
        .setIdNumber("9001011234087")
        .setLogin(true)
        .setUserType(UserType.PRO)
        .build();

        Car car = new Car.Builder()
                .setBrand("Toyota")
                .setModel("Corolla")
                .setType("Sedan")
                .setPricePerDay(BigDecimal.valueOf(500))
                .setSeatCapacity(5)
                .setBootCapacity(400)
                .setEngineCapacity(1.8f)
                .setTransmission("Automatic")
                .setDescription("Reliable sedan")
                .setCollectionLocation("Cape Town")
                .setIsAvailable(true)
                .setProUser(proUser)
                .build();

        Booking booking = BookingFactory.create(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 5),
                BigDecimal.valueOf(2000),
                BookingStatus.PENDING,
                user,
                car
        );

        System.out.println(booking);

        assertNotNull(booking);
        assertEquals(BookingStatus.PENDING, booking.getBookingStatus());
        assertEquals(user, booking.getUser());
        assertEquals(car, booking.getCar());
        assertEquals(BigDecimal.valueOf(2000), booking.getTotalPrice());
    }
}