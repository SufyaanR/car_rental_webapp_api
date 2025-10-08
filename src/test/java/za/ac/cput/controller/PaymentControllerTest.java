package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.ac.cput.domain.*;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.service.*;
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
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BasicUserService basicUserService;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BusinessUserServiceImpl businessUserService;

    @Autowired
    private ProUserServiceImpl proUserService;

    @Autowired
    private PaymentServiceImpl paymentService;

    private BasicUser basicUser;
    private BusinessUser businessUser;
    private ProUser proUser;
    private Car car;
    private Booking booking;
    private Payment payment;

    @BeforeEach
    void setup() {
        basicUser = new BasicUser.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setUsername("johndoe")
                .setPassword("password123")
                .setEmail("john@example.com")
                .setPhoneNumber("0821234567")
                .build();
        basicUser = basicUserService.register(basicUser);

        businessUser = new BusinessUser.Builder()
                .setFirstName("Alice")
                .setLastName("Johnson")
                .setUsername("aliceBiz")
                .setPassword("password123")
                .setEmail("alice.biz@example.com")
                .setPhoneNumber("0829876543")
                .setUserType(UserType.BUSINESS)
                .build();
        businessUser = businessUserService.register(businessUser);

        proUser = new ProUser.Builder()
                .setFirstName("Bob")
                .setLastName("Smith")
                .setUsername("bobPro")
                .setPassword("password123")
                .setEmail("bob.pro@example.com")
                .setPhoneNumber("0831234567")
                .setUserType(UserType.PRO)
                .build();
        proUser = proUserService.register(proUser);

        car = new Car.Builder()
                .setBrand("Toyota")
                .setModel("Corolla")
                .setType("Sedan")
                .setPricePerDay(BigDecimal.valueOf(500))
                .setSeatCapacity(5)
                .setBootCapacity(450f)
                .setEngineCapacity(1.8f)
                .setTransmission("Automatic")
                .setDescription("Reliable sedan")
                .setCollectionLocation("Cape Town")
                .setIsAvailable(true)
                .setBusinessUser(businessUser)
                .build();
        car = carService.save(car);

        booking = new Booking.Builder()
                .setStartDate(LocalDate.now())
                .setEndDate(LocalDate.now().plusDays(3))
                .setTotalPrice(BigDecimal.valueOf(1500))
                .setBookingStatus(BookingStatus.PENDING)
                .setUser(basicUser)
                .setCar(car)
                .build();
        booking = bookingService.save(booking);

        payment = PaymentFactory.create(
                12345678,
                "John Doe",
                LocalDate.now().plusYears(2),
                "123",
                BigDecimal.valueOf(1500),
                null,
                null,
                null,
                basicUser,
                booking,
                businessUser,
                null
        );
        payment = paymentService.save(payment);
        paymentService.processPayment(payment);

         Payment paymentPro = PaymentFactory.create(
            87654321,
            "John Doe",
            LocalDate.now().plusYears(2),
            "321",
            BigDecimal.valueOf(2000),
            null,
            null,
            null,
            basicUser,
            booking,
            null,
            proUser
    );
    paymentPro = paymentService.save(paymentPro);
    paymentService.processPayment(paymentPro);
    }

    @Test
    void testFindPaymentById() throws Exception {
        mockMvc.perform(get("/payment/{id}", payment.getPaymentId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(1500))
                .andExpect(jsonPath("$.user.userId").value(basicUser.getUserId()))
                .andExpect(jsonPath("$.businessUser.userId").value(businessUser.getUserId()))
                .andExpect(jsonPath("$.paymentStatus").value("SUCCESSFUL"));
    }

    @Test
    void testFindAllPayments() throws Exception {
        mockMvc.perform(get("/payment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testUpdatePayment() throws Exception {
        Payment updates = PaymentFactory.create(
                0,
                null,
                null,
                null,
                BigDecimal.valueOf(1600),
                null,
                null,
                null,
                payment.getUser(),
                null,
                payment.getBusinessUser(),
                null
        );

        mockMvc.perform(patch("/payment/{id}", payment.getPaymentId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(1600));
    }

    @Test
    void testDeletePayment() throws Exception {
        mockMvc.perform(delete("/payment/{id}", payment.getPaymentId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testCreatePaymentForBooking_WithProUserRecipient() throws Exception {
        car = new Car.Builder()
                .setCarId(car.getCarId())
                .setBrand(car.getBrand())
                .setModel(car.getModel())
                .setType(car.getType())
                .setPricePerDay(car.getPricePerDay())
                .setSeatCapacity(car.getSeatCapacity())
                .setBootCapacity(car.getBootCapacity())
                .setEngineCapacity(car.getEngineCapacity())
                .setTransmission(car.getTransmission())
                .setDescription(car.getDescription())
                .setCollectionLocation(car.getCollectionLocation())
                .setIsAvailable(car.getIsAvailable())
                .setProUser(proUser)
                .build();
        car = carService.save(car);

        Payment paymentPro = PaymentFactory.create(
                87654321,
                "John Doe",
                LocalDate.now().plusYears(2),
                "321",
                BigDecimal.valueOf(1500),
                null,
                null,
                null,
                basicUser,
                booking,
                null,
                proUser
        );

        mockMvc.perform(post("/payment/booking/{bookingId}", booking.getBookingId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentPro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.proUser.userId").value(proUser.getUserId()))
                .andExpect(jsonPath("$.user.userId").value(basicUser.getUserId()))
                .andExpect(jsonPath("$.paymentStatus").value("SUCCESSFUL"));
    }

    @Test
    void testFindPaymentsByBasicUserId() throws Exception {
        mockMvc.perform(get("/payment/basicUser/{userId}", basicUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // basicUser has 2 payments
                .andExpect(jsonPath("$[0].user.userId").value(basicUser.getUserId()));
    }

    @Test
    void testFindPaymentsByBusinessUserId() throws Exception {
        mockMvc.perform(get("/payment/businessUser/{businessUserId}", businessUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].businessUser.userId").value(businessUser.getUserId()));
    }

    @Test
    void testFindPaymentsByProUserId() throws Exception {
        mockMvc.perform(get("/payment/proUser/{proUserId}", proUser.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].proUser.userId").value(proUser.getUserId()));
    }
}
