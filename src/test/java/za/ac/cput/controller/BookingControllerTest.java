package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.*;
import za.ac.cput.factory.BusinessUserFactory;
import za.ac.cput.factory.CarFactory;
import za.ac.cput.factory.ProUserFactory;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.BusinessUserServiceImpl;
import za.ac.cput.service.CarServiceImpl;
import za.ac.cput.service.ProUserServiceImpl;
import za.ac.cput.service.BasicUserService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BasicUserService basicUserService;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private BusinessUserServiceImpl businessUserService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ProUserServiceImpl proUserService;

    private BasicUser basicUser;
    private BusinessUser businessUser;
    private ProUser proUser;
    private Car businessCar;
    private Car proCar;
    private Booking booking;
    private Booking proBooking;

    @BeforeEach
    void setup() {
        // BusinessUser and car
        businessUser = BusinessUserFactory.createBusinessUser(
                "Owner",
                "Smith",
                LocalDate.of(1980, 5, 5),
                "1234567890123",
                "owner@example.com",
                "0829876543",
                UserType.BUSINESS,
                "owner123",
                "password",
                true,
                "ABSA",
                "Owner Smith",
                111111,
                "Savings",
                "Owner Motors",
                "BR123456"
        );
        businessUser = businessUserService.save(businessUser);

        businessCar = CarFactory.create(
                null,
                "Toyota",
                "Corolla",
                "Sedan",
                BigDecimal.valueOf(500),
                5,
                400f,
                1.6f,
                "Automatic",
                "Reliable sedan",
                "Cape Town",
                true,
                businessUser,
                null
        );
        businessCar = carService.save(businessCar);

        // ProUser and car
        proUser = ProUserFactory.create(
                "Bob",
                "Smith",
                "bobPro",
                "password",
                "bob.pro@example.com",
                "0831234567",
                LocalDate.of(1988, 2, 2),
                "2345678901234",
                true,
                "ABSA",
                "Bob Smith",
                654321,
                "Savings"
        );
        proUser = proUserService.save(proUser);

        proCar = CarFactory.create(
                null,
                "Honda",
                "Civic",
                "Sedan",
                BigDecimal.valueOf(600),
                5,
                400f,
                1.8f,
                "Manual",
                "Sporty car",
                "Cape Town",
                true,
                null,
                proUser
        );
        proCar = carService.save(proCar);

        basicUser = new BasicUser.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setUsername("johndoe")
                .setPassword("password123")
                .setEmail("john.doe@example.com")
                .setPhoneNumber("0821234567")
                .setDateOfBirth(LocalDate.of(1990, 1, 1))
                .setIdNumber("9001011234567")
                .setLogin(true)
                .setUserType(UserType.BASIC)
                .build();
        basicUser = basicUserService.save(basicUser);

        booking = new Booking.Builder()
                .setStartDate(LocalDate.now().plusDays(1))
                .setEndDate(LocalDate.now().plusDays(3))
                .setTotalPrice(BigDecimal.valueOf(1500))
                .setCar(businessCar)
                .setBookingStatus(BookingStatus.PENDING)
                .setUser(basicUser)
                .build();
        booking = bookingService.save(booking);

        proBooking = new Booking.Builder()
                .setStartDate(LocalDate.now().plusDays(5))
                .setEndDate(LocalDate.now().plusDays(7))
                .setTotalPrice(BigDecimal.valueOf(2000))
                .setCar(proCar)
                .setBookingStatus(BookingStatus.PENDING)
                .setUser(basicUser)
                .build();
        proBooking = bookingService.save(proBooking);
    }

    @Test
    void testCreateBooking() throws Exception {
        mockMvc.perform(post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.userId").value(basicUser.getUserId()))
                .andExpect(jsonPath("$.car.carId").value(businessCar.getCarId()))
                .andExpect(jsonPath("$.totalPrice").value(booking.getTotalPrice().toString()))
                .andExpect(jsonPath("$.bookingStatus").value("PENDING"));
    }

    @Test
    void testCancelBooking() throws Exception {
        Booking saved = bookingService.save(booking);

        mockMvc.perform(post("/api/bookings/{id}/cancel", saved.getBookingId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingStatus").value("CANCELLED"));
    }

    @Test
    void testConfirmBooking() throws Exception {
        Booking saved = bookingService.save(booking);

        mockMvc.perform(post("/api/bookings/{id}/confirm", saved.getBookingId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingStatus").value("CONFIRMED"));
    }

    @Test
    void testFindById() throws Exception {
        Booking saved = bookingService.save(booking);

        mockMvc.perform(get("/api/bookings/{id}", saved.getBookingId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(saved.getBookingId()))
                .andExpect(jsonPath("$.totalPrice").value(saved.getTotalPrice().toString()));
    }

    @Test
    void testUpdateBooking() throws Exception {
        Booking saved = bookingService.save(booking);

        Booking updates = new Booking.Builder()
                .setTotalPrice(BigDecimal.valueOf(2000))
                .build();

        mockMvc.perform(patch("/api/bookings/{id}", saved.getBookingId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value("2000"));
    }

    @Test
    void testDeleteBooking() throws Exception {
        Booking saved = bookingService.save(booking);

        mockMvc.perform(delete("/api/bookings/{id}", saved.getBookingId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testFindAllBookings() throws Exception {
        bookingService.save(booking);

        mockMvc.perform(get("/api/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookingId").exists());
    }

   @Test
void testFindBookingsByBusinessUser() throws Exception {
    mockMvc.perform(get("/api/bookings/businessUser/{userId}", businessUser.getUserId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].car.carId").value(businessCar.getCarId()));
}

@Test
void testFindBookingsByProUser() throws Exception {
    mockMvc.perform(get("/api/bookings/proUser/{userId}", proUser.getUserId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].car.carId").value(proCar.getCarId()));
}

}
