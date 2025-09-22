package za.ac.cput.controller;

import za.ac.cput.domain.BusinessUser;
import za.ac.cput.service.BusinessUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public BusinessUser register(@RequestBody BusinessUser user) {
        return businessUserService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean success = businessUserService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (success) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }

    @PostMapping
    public BusinessUser save(@RequestBody BusinessUser user) {
        return businessUserService.save(user);
    }

    @GetMapping("/{id}")
    public BusinessUser findById(@PathVariable Long id) {
        return businessUserService.findById(id);
    }

    @GetMapping("/username/{username}")
    public Optional<BusinessUser> findByUsername(@PathVariable String username) {
        return businessUserService.findByUsername(username);
    }

    @GetMapping
    public List<BusinessUser> findAll() {
        return businessUserService.findAll();
    }

    @PatchMapping("/{id}")
    public BusinessUser updateBusinessUser(@PathVariable Long id, @RequestBody BusinessUser updates) {
        return businessUserService.update(id, updates);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        businessUserService.deleteById(id);
    }
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
