package za.ac.cput.controller;

import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Car;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.CarServiceImpl;
import za.ac.cput.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    private final BookingService bookingService;
    private final BasicUserService basicUserService;
    private final CarServiceImpl carService;

    @Autowired
    public BookingController(BookingService bookingService, BasicUserService basicUserService, CarServiceImpl carService) {
        this.bookingService = bookingService;
        this.basicUserService = basicUserService;
        this.carService = carService;
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestParam Long userId, @RequestParam Long carId, @RequestBody Booking bookingRequest) {
        BasicUser user = basicUserService.findById(userId);
        Car car = carService.findById(carId);

        Booking booking = new Booking.Builder()
                .setStartDate(bookingRequest.getStartDate())
                .setEndDate(bookingRequest.getEndDate())
                .setTotalPrice(bookingRequest.getTotalPrice())
                .setCar(car)
                .setBookingStatus(bookingRequest.getBookingStatus())
                .setUser(user)
                .build();

        Booking savedBooking = bookingService.save(booking);
        return ResponseEntity.ok(savedBooking);
    }

@PatchMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id,
                                                 @RequestBody Booking updates) {
        Booking updatedBooking = bookingService.update(id, updates);
        return ResponseEntity.ok(updatedBooking);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.findById(id);
        booking.cancelBooking();
        bookingService.save(booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
        Booking booking = bookingService.findById(id);
        booking.confirmBooking();
        bookingService.save(booking);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}