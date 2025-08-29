package za.ac.cput.controller;

import za.ac.cput.domain.BusinessUser;
import za.ac.cput.service.BusinessUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/business-users")
@CrossOrigin(origins = "http://localhost:5173")
public class BusinessUserController {

    private final BusinessUserServiceImpl businessUserService;

    @Autowired
    public BusinessUserController(BusinessUserServiceImpl businessUserService) {
        this.businessUserService = businessUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<BusinessUser> register(@RequestBody BusinessUser user) {
        BusinessUser savedUser = businessUserService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean success = businessUserService.login(username, password);
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping
    public ResponseEntity<BusinessUser> save(@RequestBody BusinessUser user) {
        return ResponseEntity.ok(businessUserService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessUser> findById(@PathVariable Long id) {
        return ResponseEntity.ok(businessUserService.findById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Optional<BusinessUser>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(businessUserService.findByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<BusinessUser>> findAll() {
        return ResponseEntity.ok(businessUserService.findAll());
    }

   @PatchMapping("/{id}")
    public ResponseEntity<BusinessUser> updateBusinessUser(@PathVariable Long id, @RequestBody BusinessUser updates) {
        BusinessUser updatedUser = businessUserService.update(id, updates);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        businessUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
