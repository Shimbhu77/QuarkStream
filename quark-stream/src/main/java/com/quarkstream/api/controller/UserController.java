package com.quarkstream.api.controller;

import com.quarkstream.api.exceptions.UserException;
import com.quarkstream.api.model.User;
import com.quarkstream.api.model.dto.UserDTO;
import com.quarkstream.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("rest/user")
@Tag(name = "User API docs",description = "Different types of CRUD operations for User.")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Operation(summary = "Sign up endpoint for user creation.")
    @PostMapping("/sign-up")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws UserException {

        // encoding password to hash password using hashing
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = userService.signupUser(userDTO);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @Operation(summary = "Sign in endpoint for user login.")
    @GetMapping("/public/sign-in")
    public ResponseEntity<User> signInUser(Authentication authentication) throws UserException {

        User user = userService.findUserByEmail(authentication.getName());

        if(Objects.nonNull(user))
           return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);

        throw new BadCredentialsException("Invalid username or password.");
    }

    @Operation(summary = "Endpoint for getting current logged in user details.")
    @GetMapping("/secure/my-profile")
    public ResponseEntity<User> loggedInUser() throws UserException {

        User user = userService.loggedInUser();

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
