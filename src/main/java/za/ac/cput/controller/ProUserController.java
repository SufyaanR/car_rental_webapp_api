package za.ac.cput.controller;

import za.ac.cput.domain.ProUser;
import za.ac.cput.service.ProUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProUser> register(@RequestBody ProUser user) {
        ProUser savedUser = proUserService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean success = proUserService.login(username, password);
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping
    public ResponseEntity<ProUser> save(@RequestBody ProUser user) {
        return ResponseEntity.ok(proUserService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProUser> findById(@PathVariable Long id) {
        return ResponseEntity.ok(proUserService.findById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Optional<ProUser>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(proUserService.findByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<ProUser>> findAll() {
        return ResponseEntity.ok(proUserService.findAll());
    }

   @PatchMapping("/{id}")
    public ResponseEntity<ProUser> updateProUser(
        @PathVariable Long id,
        @RequestBody ProUser updatedUserRequest) {

    ProUser updatedUser = proUserService.update(id, updatedUserRequest);
    return ResponseEntity.ok(updatedUser);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        proUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
