package com.vyasmeet.rest.webservices.restfulwebservices.controller;

import com.vyasmeet.rest.webservices.restfulwebservices.dto.UserDto;
import com.vyasmeet.rest.webservices.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDto userService;

    // GET /users
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    // GET /users/{id}
    @GetMapping("/users/{id}")
    public User findUserByID(@PathVariable int id) {
        return userService.findWithID(id);
    }
}
