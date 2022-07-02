package com.in28minutes.demo.controller;

import com.in28minutes.demo.dao.UserDao;
import com.in28minutes.demo.exception.UserNotFoundException;
import com.in28minutes.demo.model.User;
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
    private UserDao userDao;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDao.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = userDao.save(user);
        // you will find the path to new resource(created user) in response headers
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable int id) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new UserNotFoundException("id " + id);
        }
        return user;

//        Link link = linkTo(methodOn(this.getClass()).retrieveAllUsers()).withSelfRel();
//        List<User> userList = new ArrayList<>();
//        userList.add(user);
//        return CollectionModel.of(userList, link);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        User user = userDao.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("id " + id);
        }
    }


}
