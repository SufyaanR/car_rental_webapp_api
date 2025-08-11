package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ProUser;
import za.ac.cput.service.IProUserService;
import java.util.List;

@RestController
@RequestMapping("/api/pro-users")
public class ProUserController {

    private final IProUserService proUserService;

    @Autowired
    public ProUserController(IProUserService proUserService) {
        this.proUserService = proUserService;
    }

    @PostMapping
    public ResponseEntity<ProUser> create(@RequestBody ProUser proUser) {
        ProUser created = proUserService.create(proUser);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProUser> read(@PathVariable String userId) {
        ProUser proUser = proUserService.read(userId);
        return proUser != null ?
                ResponseEntity.ok(proUser) :
                ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<ProUser> update(@RequestBody ProUser proUser) {
        ProUser updated = proUserService.update(proUser);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable String userId) {
        boolean success = proUserService.delete(userId);
        return success ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ProUser>> getAll() {
        List<ProUser> proUsers = proUserService.getAll();
        return ResponseEntity.ok(proUsers);
    }
}