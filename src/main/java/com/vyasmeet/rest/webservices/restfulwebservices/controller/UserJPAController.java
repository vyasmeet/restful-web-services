package com.vyasmeet.rest.webservices.restfulwebservices.controller;

import com.vyasmeet.rest.webservices.restfulwebservices.dto.UserDto;
import com.vyasmeet.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.vyasmeet.rest.webservices.restfulwebservices.model.User;
import com.vyasmeet.rest.webservices.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

    @Autowired
    private UserRepository userRepository;

    // GET /jpa/users
    @GetMapping("/jpa/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // GET /jpa/users/{id}
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> findUserByID(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("Can't find ID: "+id);
        }

        // Created EntityModel of User for passing links with Data via Hateoas
        EntityModel<User> model = EntityModel.of(user.get());
        // Adding link to getUsers - For fetching all users API
        WebMvcLinkBuilder linkToUsers = linkTo(
                methodOn(this.getClass())
                        .getUsers());
        model.add(linkToUsers.withRel("get-all-users"));
        return model;
    }

    // DELETE /jpa/user/{id}
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserForID(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    // POST /jpa/users
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User newUser = userRepository.save(user);
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
