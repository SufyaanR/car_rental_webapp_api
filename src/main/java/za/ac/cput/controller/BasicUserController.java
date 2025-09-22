package za.ac.cput.controller;

import za.ac.cput.domain.BasicUser;
import za.ac.cput.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/basic-users")
@CrossOrigin(origins = "http://localhost:5173")
public class BasicUserController {

    private final BasicUserService basicUserService;

    @Autowired
    public BasicUserController(BasicUserService basicUserService) {
        this.basicUserService = basicUserService;
    }

    @PostMapping("/register")
    public BasicUser register(@RequestBody BasicUser user) {
        return basicUserService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean success = basicUserService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (success) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }

    @PostMapping
    public BasicUser save(@RequestBody BasicUser user) {
        return basicUserService.save(user);
    }

    @GetMapping("/{id}")
    public BasicUser findById(@PathVariable Long id) {
        return basicUserService.findById(id);
    }

    @GetMapping("/username/{username}")
    public Optional<BasicUser> findByUsername(@PathVariable String username) {
        return basicUserService.findByUsername(username);
    }

    @GetMapping
    public List<BasicUser> findAll() {
        return basicUserService.findAll();
    }

    @PatchMapping("/{id}")
    public BasicUser updateBasicUser(@PathVariable Long id, @RequestBody BasicUser updates) {
        return basicUserService.update(id, updates);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        basicUserService.deleteById(id);
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
