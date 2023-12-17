package com.dtech.Ortaklasalim.controller;

import com.dtech.Ortaklasalim.model.User;
import com.dtech.Ortaklasalim.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<User> getUser(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping("/add")
    public @ResponseBody HttpStatus addUser(@RequestBody User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }

    @PutMapping("/update/{id}")
    public @ResponseBody HttpStatus updateUser(@RequestBody User user) {
        User newUser = new User();

        if (user != null) {
            newUser.setId(user.getId());
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setPhone_number(user.getPhone_number());
            newUser.setOffice_name(user.getOffice_name());
            newUser.setOffice_address(user.getOffice_address());

            try {
                userRepository.save(user);
            } catch (Exception e) {
                return HttpStatus.BAD_REQUEST;
            }
        } else {
            return HttpStatus.NO_CONTENT;
        }

        return HttpStatus.OK;
    }
}
