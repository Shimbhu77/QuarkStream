package com.quarkstream.api.service.implementation;

import com.quarkstream.api.exceptions.UserException;
import com.quarkstream.api.model.User;
import com.quarkstream.api.model.dto.UserDTO;
import com.quarkstream.api.repository.UserRepository;
import com.quarkstream.api.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

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

        User existingUser = userRepository.findByEmail(userDTO.getEmail());

        if(Objects.nonNull(existingUser))
        {
            throw new UserException("User already exists with this email : "+userDTO.getEmail());
        }

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
     * @return
     * @throws UserException
     */
    @Override
    public User loggedInUser() throws UserException {

        SecurityContext securityContext = SecurityContextHolder.getContext();

        Authentication authentication = securityContext.getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email);
    }

    /**
     * @return
     * @throws UserException
     */
    @Override
    public User findUserByEmail(String email) throws UserException {

        User user = userRepository.findByEmail(email);

        if(Objects.isNull(user))
        {
            throw new UserException("User can't exists with this email : "+email);
        }

        return user;
    }
}
