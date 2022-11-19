package com.spring.ecommerce.service;

import com.spring.ecommerce.dto.AddUserDTO;
import com.spring.ecommerce.model.User;
import com.spring.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public User addUser(AddUserDTO addUserDTO){
//        User userToBeSaved = new User();
//        //User foundUser = userRepository.findById(addUserDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
//       userToBeSaved.setName(addUserDTO.getName());
//       userToBeSaved.
//        return userRepository.save(addUserDTO);
//    }
}
