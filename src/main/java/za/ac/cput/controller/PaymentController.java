package za.ac.cput.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Payment;
import za.ac.cput.service.IPaymentService;


@RestController
@RequestMapping("/Payment")
public class PaymentController {

    private final IPaymentService paymentService;

    @Autowired
    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

        @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment payment) {
        Payment saved = paymentService.save(payment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> read(@PathVariable Long paymentId) {
        Payment payment = paymentService.read(paymentId);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long paymentId) {
        paymentService.delete(paymentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        return ResponseEntity.ok(paymentService.findall());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> update(@PathVariable Long paymentId, @RequestBody Payment payment) {
        if (!paymentId.equals(payment.getPaymentId())) {
            return ResponseEntity.badRequest().build();
        }
        Payment updated = paymentService.save(payment);
        return ResponseEntity.ok(updated);
    }
    
    
}
