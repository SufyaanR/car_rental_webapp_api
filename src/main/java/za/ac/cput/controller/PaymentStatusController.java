package za.ac.cput.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.service.IPaymentStatusService;
import java.util.List;

@RestController
@RequestMapping("/api/payment-statuses")
public class PaymentStatusController {
    private final IPaymentStatusService service;

    public PaymentStatusController(IPaymentStatusService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PaymentStatus>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<PaymentStatus> getByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(service.findByName(name));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}