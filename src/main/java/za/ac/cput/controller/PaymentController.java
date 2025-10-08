package za.ac.cput.controller;

import za.ac.cput.domain.*;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    private final PaymentServiceImpl paymentService;
    private final BookingService bookingService;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService, BookingService bookingService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }

    @PostMapping("/booking/{bookingId}")
    public Payment createPaymentForBooking(
            @PathVariable Long bookingId,
            @RequestBody Payment paymentRequest
    ) {
        Booking booking = bookingService.findById(bookingId);
        BasicUser payer = booking.getUser();

        ProUser proRecipient = booking.getCar().getProUser();
        BusinessUser businessRecipient = booking.getCar().getBusinessUser();

        Payment.Builder builder = new Payment.Builder()
                .setAmount(paymentRequest.getAmount())
                .setPaymentDate(paymentRequest.getPaymentDate())
                .setPaymentTime(paymentRequest.getPaymentTime())
                .setBooking(booking)
                .setUser(payer)
                .setCardNumber(paymentRequest.getCardNumber())
                .setNameOfCardHolder(paymentRequest.getNameOfCardHolder())
                .setExpiryDate(paymentRequest.getExpiryDate())
                .setCcv(paymentRequest.getCcv())
                .setPaymentStatus(paymentRequest.getPaymentStatus());

        if (proRecipient != null) {
            builder.setProUser(proRecipient);
        } else if (businessRecipient != null) {
            builder.setBusinessUser(businessRecipient);
        } else {
            throw new IllegalStateException("Booking car must have a recipient (ProUser or BusinessUser).");
        }

        Payment payment = builder.build();
        paymentService.processPayment(payment);
        return payment;
    }

    @GetMapping("/{id}")
    public Payment findById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @GetMapping
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @PatchMapping("/{id}")
    public Payment updatePayment(
        @PathVariable Long id,
        @RequestBody Payment paymentUpdates) {

        return paymentService.update(id, paymentUpdates);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable Long id) {
        paymentService.deleteById(id);
    }

     @GetMapping("/basicUser/{userId}")
    public List<Payment> findByBasicUserId(@PathVariable Long userId) {
        return paymentService.findPaymentsByBasicUserId(userId);
    }

    @GetMapping("/proUser/{proUserId}")
    public List<Payment> findByProUserId(@PathVariable Long proUserId) {
        return paymentService.findPaymentsByProUserId(proUserId);
    }

    @GetMapping("/businessUser/{businessUserId}")
    public List<Payment> findByBusinessUserId(@PathVariable Long businessUserId) {
        return paymentService.findPaymentsByBusinessUserId(businessUserId);
    }
}

