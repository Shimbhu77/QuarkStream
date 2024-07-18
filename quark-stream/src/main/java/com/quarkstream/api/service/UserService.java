package com.quarkstream.api.service;

import com.quarkstream.api.exceptions.UserException;
import com.quarkstream.api.model.UserModel;
import com.quarkstream.api.model.dto.UserDTO;

public interface UserService {
    UserModel signupUser(UserDTO userDTO) throws UserException;
    String loginUser(UserDTO userDTO) throws UserException;
    UserModel loggedInUser() throws UserException;
}
