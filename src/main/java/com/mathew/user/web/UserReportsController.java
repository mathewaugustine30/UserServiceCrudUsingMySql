package com.mathew.user.web;


import com.mathew.user.service.UserService;
import com.mathew.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userreports")
public class UserReportsController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createStudent(@RequestBody User user) {
        User createResponse = userService.save(user);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateStudent(@RequestBody User user) {
        User updateResponse = userService.update(user);
        return new ResponseEntity<>(updateResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User getResponse = userService.get(id);
        return new ResponseEntity<>(getResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.delete(id);
        return "Record deleted succesfully";
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        List<User> getAllResponse = userService.getAllUsers();
        return new ResponseEntity<>(getAllResponse, HttpStatus.ACCEPTED);
    }
}
