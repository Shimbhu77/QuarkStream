package com.quarkstream.api.service;

import com.quarkstream.api.exceptions.UserException;
import com.quarkstream.api.model.User;
import com.quarkstream.api.model.dto.UserDTO;

public interface UserService {
    User signupUser(UserDTO userDTO) throws UserException;
    String loginUser(UserDTO userDTO) throws UserException;
    User loggedInUser() throws UserException;
}
