package za.ac.cput.service;

import za.ac.cput.domain.*;
import za.ac.cput.factory.*;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.CarServiceImpl;
import za.ac.cput.service.BasicUserService;
import za.ac.cput.service.BusinessUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private BasicUserService basicUserService;

    @Autowired
    private BusinessUserServiceImpl businessUserService;

    private Booking booking;

    @BeforeEach
    void setup() {
        BusinessUser businessUser = BusinessUserFactory.createBusinessUser(
                "John", 
                "Doe", 
                LocalDate.of(1985, 1, 1), 
                "1234567890123",
                "john@business.", 
                "0821234567", 
                UserType.BUSINESS,
                "johndoe", 
                "password", 
                true,
                "ABSA", 
                "John Doe", 
                123456, 
                "Savings",
                "JD Motors", 
                "BR123456"
        );
        businessUser = businessUserService.save(businessUser);

        Car car = CarFactory.create(
                new byte[10],
                "Toyota",
                "Corolla",
                "Sedan",
                BigDecimal.valueOf(500),
                5,
                450.5f,
                1.6f,
                "Automatic",
                "Reliable car",
                "Johannesburg",
                true,
                businessUser,
                null
        );
        car = carService.save(car);

        BasicUser user = BasicUserFactory.create(
                "Alice",
                "Smith",
                "alice123",
                "password",
                "alice@example.",
                "0829876543",
                LocalDate.of(1990, 5, 20),
                "8901234567890",
                true
        );
        user = basicUserService.save(user);

        booking = BookingFactory.create(
        LocalDate.of(2025, 9, 1),
        LocalDate.of(2025, 9, 5),
        BigDecimal.valueOf(2000),
        BookingStatus.PENDING,
        user,
        car
);
        booking = bookingService.save(booking);
    }

    @Test
    void testSaveBooking() {
        assertNotNull(booking.getBookingId());
    }

    @Test
    void testFindById() {
        Booking found = bookingService.findById(booking.getBookingId());
        assertEquals(booking.getBookingId(), found.getBookingId());
    }

    @Test
    void testFindAll() {
        List<Booking> bookings = bookingService.findAll();
        assertTrue(bookings.size() >= 1);
    }

    @Test
    void testCancelBooking() {
        bookingService.cancelBooking(booking.getBookingId());
        Booking updated = bookingService.findById(booking.getBookingId());
        assertEquals(BookingStatus.CANCELLED, updated.getBookingStatus());
    }

    @Test
    void testConfirmBooking() {
        bookingService.confirmBooking(booking.getBookingId());
        Booking updated = bookingService.findById(booking.getBookingId());
        assertEquals(BookingStatus.CONFIRMED, updated.getBookingStatus());
    }

    @Test
    void testDeleteBooking() {
        Long id = booking.getBookingId();
        bookingService.deleteById(id);
        assertThrows(RuntimeException.class, () -> bookingService.findById(id));
    }

   @Test
void testUpdateBooking() {
    Booking updates = new Booking.Builder()
            .setStartDate(LocalDate.of(2025, 9, 2)) 
            .setEndDate(LocalDate.of(2025, 9, 6))   
            .setTotalPrice(BigDecimal.valueOf(2200))
            .setBookingStatus(BookingStatus.CONFIRMED)
            .build();

    Booking updated = bookingService.update(booking.getBookingId(), updates);

    assertEquals(BigDecimal.valueOf(2200), updated.getTotalPrice());
    assertEquals(LocalDate.of(2025, 9, 2), updated.getStartDate());
    assertEquals(LocalDate.of(2025, 9, 6), updated.getEndDate());
    assertEquals(BookingStatus.CONFIRMED, updated.getBookingStatus());
    assertEquals(booking.getUser(), updated.getUser());
    assertEquals(booking.getCar(), updated.getCar());   
}
}
