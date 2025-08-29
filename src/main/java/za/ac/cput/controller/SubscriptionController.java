package za.ac.cput.controller;

import za.ac.cput.domain.*;
import za.ac.cput.service.BusinessUserServiceImpl;
import za.ac.cput.service.ProUserServiceImpl;
import za.ac.cput.service.SubscriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Subscription")
@CrossOrigin(origins = "http://localhost:5173")
public class SubscriptionController {

    private final SubscriptionServiceImpl subscriptionPaymentService;
    private final ProUserServiceImpl proUserService;
    private final BusinessUserServiceImpl businessUserService;

    @Autowired
    public SubscriptionController(SubscriptionServiceImpl subscriptionPaymentService,
                                         ProUserServiceImpl proUserService,
                                         BusinessUserServiceImpl businessUserService) {
        this.subscriptionPaymentService = subscriptionPaymentService;
        this.proUserService = proUserService;
        this.businessUserService = businessUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<SubscriptionPayment> createPayment(
            @RequestParam Long userId,
            @RequestParam String userType,
            @RequestBody SubscriptionPayment paymentRequest) {

        SubscriptionPayment savedPayment;

        if (userType.equalsIgnoreCase("PRO")) {
            ProUser proUser = proUserService.findById(userId);
            savedPayment = subscriptionPaymentService.createForProUser(proUser, paymentRequest);
        } else if (userType.equalsIgnoreCase("BUSINESS")) {
            BusinessUser businessUser = businessUserService.findById(userId);
            savedPayment = subscriptionPaymentService.createForBusinessUser(businessUser, paymentRequest);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionPayment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionPaymentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionPayment>> findAll() {
        return ResponseEntity.ok(subscriptionPaymentService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubscriptionPayment> updatePayment(
            @PathVariable Long id,
            @RequestBody SubscriptionPayment updatedPayment) {

        SubscriptionPayment payment = subscriptionPaymentService.update(id, updatedPayment);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        subscriptionPaymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
