package za.ac.cput.controller;

import za.ac.cput.domain.BasicUser;
import za.ac.cput.service.BasicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BasicUser> register(@RequestBody BasicUser user) {
        return ResponseEntity.ok(basicUserService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<BasicUser> userOpt = basicUserService.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().login(username, password)) {
            return ResponseEntity.ok(userOpt.get().getUserId());
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping
    public ResponseEntity<BasicUser> save(@RequestBody BasicUser user) {
        return ResponseEntity.ok(basicUserService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicUser> findById(@PathVariable Long id) {
        return ResponseEntity.ok(basicUserService.findById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Optional<BasicUser>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(basicUserService.findByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<BasicUser>> findAll() {
        return ResponseEntity.ok(basicUserService.findAll());
    }

   @PatchMapping("/{id}")
    public ResponseEntity<BasicUser> updateBasicUser(@PathVariable Long id, @RequestBody BasicUser updates) {
        BasicUser updatedUser = basicUserService.update(id, updates);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        basicUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
