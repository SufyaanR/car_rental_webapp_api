package za.ac.cput.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ProUser;
import za.ac.cput.service.IProUserService;
import java.util.List;

@RestController
@RequestMapping("/api/pro-users")
public class ProUserController {
    private final IProUserService service;

    public ProUserController(IProUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProUser> create(@RequestBody ProUser proUser) {
        return ResponseEntity.ok(service.create(proUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProUser> read(@PathVariable String id) {
        ProUser user = service.read(id);
        return user != null ?
                ResponseEntity.ok(user) :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ProUser>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}