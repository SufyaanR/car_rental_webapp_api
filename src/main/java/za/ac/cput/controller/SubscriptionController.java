package za.ac.cput.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.SubscriptionPayment;
import za.ac.cput.service.ISubscriptionService;

@RestController
@RequestMapping("/Subscription")
public class SubscriptionController {

    private final ISubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
        }

    @PostMapping
    public ResponseEntity<SubscriptionPayment> create(@RequestBody SubscriptionPayment subscriptionPayment) {
        SubscriptionPayment saved = subscriptionService.save(subscriptionPayment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPayment> read(@PathVariable Long subscriptionPaymentId) {
        SubscriptionPayment subscriptionPayment = subscriptionService.read(subscriptionPaymentId);
        if (subscriptionPayment != null) {
            return ResponseEntity.ok(subscriptionPayment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long subscriptionPaymentId) {
        subscriptionService.delete(subscriptionPaymentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionPayment>> findAll() {
        return ResponseEntity.ok(subscriptionService.findall());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionPayment> update(@PathVariable Long subscriptionPaymentId, @RequestBody SubscriptionPayment subscriptionPayment) {
        if (!subscriptionPaymentId.equals(subscriptionPayment.getSubscriptionPaymentId())) {
            return ResponseEntity.badRequest().build();
        }
        SubscriptionPayment updated = subscriptionService.save(subscriptionPayment);
        return ResponseEntity.ok(updated);
    }
    
}
