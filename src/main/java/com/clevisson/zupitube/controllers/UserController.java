package com.clevisson.zupitube.controllers;

import com.clevisson.zupitube.model.User;
import com.clevisson.zupitube.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<User> listVideo() {
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<User> listUserById(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping(value = "/{id}")//VER NECESSIDADE DE UPDATE COMMENT
    public ResponseEntity<User> updateUserById(@PathVariable("id") long id, @RequestBody User user) {
        return repository.findById(id)
                .map(record -> {
                    record.setFirstName(user.getFirstName());
                    record.setLastName(user.getLastName());
                    record.setUserName(user.getUserName());
                    record.setChannel(user.getChannel());
                    record.setUserPassword(user.getUserPassword());
                    User updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteVideoById(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
