package za.ac.cput.controller;

import za.ac.cput.domain.*;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Payment> createPaymentForBooking(
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
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

   @PatchMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(
        @PathVariable Long id,
        @RequestBody Payment paymentUpdates) {

    Payment updatedPayment = paymentService.update(id, paymentUpdates);
    return ResponseEntity.ok(updatedPayment);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
