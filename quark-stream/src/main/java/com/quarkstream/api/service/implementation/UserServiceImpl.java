package com.quarkstream.api.service.implementation;

import com.quarkstream.api.exceptions.UserException;
import com.quarkstream.api.model.UserModel;
import com.quarkstream.api.model.dto.UserDTO;
import com.quarkstream.api.service.UserService;

public class UserServiceImpl implements UserService {
    /**
     * @param userDTO
     * @return
     * @throws UserException
     */
    @Override
    public UserModel signupUser(UserDTO userDTO) throws UserException {
        return null;
    }

    /**
     * @param userDTO
     * @return
     * @throws UserException
     */
    @Override
    public String loginUser(UserDTO userDTO) throws UserException {
        return "";
    }

    /**
     * @return
     * @throws UserException
     */
    @Override
    public UserModel loggedInUser() throws UserException {
        return null;
    }
}
