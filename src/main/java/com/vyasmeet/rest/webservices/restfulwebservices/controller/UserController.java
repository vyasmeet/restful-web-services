package com.vyasmeet.rest.webservices.restfulwebservices.controller;

import com.vyasmeet.rest.webservices.restfulwebservices.dto.UserDto;
import com.vyasmeet.rest.webservices.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDto userService;

    // GET /users
    // GET /users/{id}
    @GetMapping("/users/{id}")
    public User findUserByID(@PathVariable int id) {
        return userService.findWithID(id);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        User newUser = userService.save(user);
    }
}
