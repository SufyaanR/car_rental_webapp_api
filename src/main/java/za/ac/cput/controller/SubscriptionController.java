package za.ac.cput.controller;

import za.ac.cput.domain.*;
import za.ac.cput.service.BusinessUserServiceImpl;
import za.ac.cput.service.ProUserServiceImpl;
import za.ac.cput.service.SubscriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public SubscriptionPayment createPayment(
            @RequestParam Long userId,
            @RequestParam String userType,
            @RequestBody SubscriptionPayment paymentRequest) {

        if (userType.equalsIgnoreCase("PRO")) {
            ProUser proUser = proUserService.findById(userId);
            return subscriptionPaymentService.createForProUser(proUser, paymentRequest);
        } else if (userType.equalsIgnoreCase("BUSINESS")) {
            BusinessUser businessUser = businessUserService.findById(userId);
            return subscriptionPaymentService.createForBusinessUser(businessUser, paymentRequest);
        } else {
            throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    @GetMapping("/{id}")
    public SubscriptionPayment findById(@PathVariable Long id) {
        return subscriptionPaymentService.findById(id);
    }

    @GetMapping
    public List<SubscriptionPayment> findAll() {
        return subscriptionPaymentService.findAll();
    }

    @PatchMapping("/{id}")
    public SubscriptionPayment updatePayment(
            @PathVariable Long id,
            @RequestBody SubscriptionPayment updatedPayment) {
        return subscriptionPaymentService.update(id, updatedPayment);
    }

   @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteById(@PathVariable Long id) {
        subscriptionPaymentService.deleteById(id);
    }

}
