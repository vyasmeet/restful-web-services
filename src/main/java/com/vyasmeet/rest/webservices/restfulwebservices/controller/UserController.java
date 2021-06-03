package com.vyasmeet.rest.webservices.restfulwebservices.controller;

import com.vyasmeet.rest.webservices.restfulwebservices.dto.UserDto;
import com.vyasmeet.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.vyasmeet.rest.webservices.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        User user = userService.findWithID(id);
        if (user == null) {
            throw new UserNotFoundException("Can't find ID: "+id);
        }
        return user;
    }

    // DELETE /user/{id}
    @DeleteMapping("/users/{id}")
    public void deleteUserForID(@PathVariable int id) {
        User deletedUser = userService.deleteByID(id);
        if (deletedUser == null) {
            // If we can't find the user to Delete for ID, return UserNotFoundException
            throw new UserNotFoundException("Can't find ID: "+id);
        }
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User newUser = userService.save(user);
        // Return the new URI back.
        // with HTTP created status code.
        // /users/{id}  newUser.getId()

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
