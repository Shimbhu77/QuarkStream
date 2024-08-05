package com.quarkstream.api.controller;

import com.quarkstream.api.exceptions.UserException;
import com.quarkstream.api.model.User;
import com.quarkstream.api.model.dto.UserDTO;
import com.quarkstream.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/sign-up")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws UserException {

        // encoding password to hash password using hashing
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = userService.signupUser(userDTO);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
