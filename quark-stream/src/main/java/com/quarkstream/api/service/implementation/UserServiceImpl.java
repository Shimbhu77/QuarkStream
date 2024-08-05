package com.quarkstream.api.service.implementation;

import com.quarkstream.api.exceptions.UserException;
import com.quarkstream.api.model.User;
import com.quarkstream.api.model.dto.UserDTO;
import com.quarkstream.api.repository.UserRepository;
import com.quarkstream.api.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {


     private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * @param userDTO
     * @return
     * @throws UserException
     */
    @Override
    public User signupUser(UserDTO userDTO) throws UserException {

        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setUserRole(userDTO.getUserRole());
        user.setEffTs(LocalDateTime.now());

        return userRepository.save(user);
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
    public User loggedInUser() throws UserException {
        return null;
    }
}
