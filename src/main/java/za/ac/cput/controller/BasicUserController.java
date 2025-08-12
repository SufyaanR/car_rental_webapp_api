package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.BasicUser;
import za.ac.cput.domain.Car;
import za.ac.cput.service.BasicUserService;
import za.ac.cput.service.CarService;

import java.util.List;

@RestController
@RequestMapping("basicuser")
public class BasicUserController {

    private BasicUserService service;

    @Autowired
    public BasicUserController(BasicUserService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public BasicUser create(@RequestBody BasicUser basicUser) {
        return service.create(basicUser);
    }

    @GetMapping("/read/{carId}")
    public BasicUser read(@PathVariable Long basicUserId) {
        return service.read(basicUserId);
    }

    @PutMapping("/update")
    public BasicUser update(@RequestBody BasicUser basicUser) {
        return service.update(basicUser);
    }

    @DeleteMapping("/delete/{basicUserId}")
    public boolean delete(@PathVariable Long basicUserId) {
        return service.delete(basicUserId);
    }

    @GetMapping("/getAll")
    public List<BasicUser> getAll() {
        return service.getAll();
    }

}
