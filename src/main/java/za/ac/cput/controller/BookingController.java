package za.ac.cput.controller;

import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.BookingStatus;
import za.ac.cput.domain.Car;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.CarServiceImpl;
import za.ac.cput.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    private final BookingService bookingService;
    private final BasicUserService basicUserService;
    private final CarServiceImpl carService;

    @Autowired
    public BookingController(BookingService bookingService, BasicUserService basicUserService,
            CarServiceImpl carService) {
        this.bookingService = bookingService;
        this.basicUserService = basicUserService;
        this.carService = carService;
    }

    @PostMapping
    public Booking save(@RequestBody Map<String, Object> payload) {
        BasicUser user = basicUserService.findById(Long.valueOf(payload.get("userId").toString()));
        Car car = carService.findById(Long.valueOf(payload.get("carId").toString()));

        Booking booking = new Booking.Builder()
                .setStartDate(LocalDate.parse(payload.get("startDate").toString()))
                .setEndDate(LocalDate.parse(payload.get("endDate").toString()))
                .setTotalPrice(new BigDecimal(payload.get("totalPrice").toString()))
                .setBookingStatus(BookingStatus.valueOf(payload.get("bookingStatus").toString()))
                .setUser(user)
                .setCar(car)
                .build();

        return bookingService.save(booking);
    }

    @PatchMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking updates) {
        return bookingService.update(id, updates);
    }

    @GetMapping("/{id}")
    public Booking findById(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @GetMapping
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    @PostMapping("/{id}/cancel")
    public Booking cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.findById(id);
        booking.cancelBooking();
        bookingService.save(booking);
        return booking;
    }

    @PostMapping("/{id}/confirm")
    public Booking confirmBooking(@PathVariable Long id) {
        Booking booking = bookingService.findById(id);
        booking.confirmBooking();
        bookingService.save(booking);
        return booking;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
    }

    @GetMapping("/proUser/{proUserId}")
    public List<Booking> getBookingsByProUser(@PathVariable Long proUserId) {
        return bookingService.findBookingsByProUserId(proUserId);
    }

    @GetMapping("/businessUser/{businessUserId}")
    public List<Booking> getBookingsByBusinessUser(@PathVariable Long businessUserId) {
        return bookingService.findBookingsByBusinessUserId(businessUserId);
    }
}