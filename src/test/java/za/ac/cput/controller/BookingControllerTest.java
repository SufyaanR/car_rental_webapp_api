package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.ac.cput.domain.*;
import za.ac.cput.factory.CarFactory;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.BusinessUserServiceImpl;
import za.ac.cput.service.CarServiceImpl;
import za.ac.cput.service.BasicUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
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

    private BasicUser basicUser;
    private BusinessUser businessUser;
    private Car car;
    private Booking booking;

    @BeforeEach
    void setup() {
        businessUser = new BusinessUser.Builder()
                .setFirstName("Owner")
                .setLastName("Smith")
                .setUsername("owner123")
                .setPassword("password")
                .setEmail("owner@example.com")
                .setPhoneNumber("0829876543")
                .setUserType(UserType.BUSINESS)
                .build();

        businessUser = businessUserService.save(businessUser);

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

        car = CarFactory.create(
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

        car = carService.save(car);

        booking = new Booking.Builder()
                .setStartDate(LocalDate.now().plusDays(1))
                .setEndDate(LocalDate.now().plusDays(3))
                .setTotalPrice(BigDecimal.valueOf(1500))
                .setCar(car)
                .setBookingStatus(BookingStatus.PENDING)
                .setUser(basicUser)
                .build();
    }

    @Test
    void testCreateBooking() throws Exception {
        mockMvc.perform(post("/api/bookings/create")
                        .param("userId", basicUser.getUserId().toString())
                        .param("carId", car.getCarId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.userId").value(basicUser.getUserId()))
                .andExpect(jsonPath("$.car.carId").value(car.getCarId()))
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
}
