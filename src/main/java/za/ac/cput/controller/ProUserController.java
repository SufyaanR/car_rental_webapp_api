package za.ac.cput.controller;

import za.ac.cput.domain.ProUser;
import za.ac.cput.service.ProUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pro-users")
@CrossOrigin(origins = "http://localhost:5173")
public class ProUserController {

    private final ProUserServiceImpl proUserService;

    @Autowired
    public ProUserController(ProUserServiceImpl proUserService) {
        this.proUserService = proUserService;
    }

    @PostMapping("/register")
    public ProUser register(@RequestBody ProUser user) {
        return proUserService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        boolean success = proUserService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (success) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }

    @PostMapping
    public ProUser save(@RequestBody ProUser user) {
        return proUserService.save(user);
    }

    @GetMapping("/{id}")
    public ProUser findById(@PathVariable Long id) {
        return proUserService.findById(id);
    }

    @GetMapping("/username/{username}")
    public Optional<ProUser> findByUsername(@PathVariable String username) {
        return proUserService.findByUsername(username);
    }

    @GetMapping
    public List<ProUser> findAll() {
        return proUserService.findAll();
    }

    @PatchMapping("/{id}")
    public ProUser updateProUser(
        @PathVariable Long id,
        @RequestBody ProUser updatedUserRequest) {
        return proUserService.update(id, updatedUserRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        proUserService.deleteById(id);
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
