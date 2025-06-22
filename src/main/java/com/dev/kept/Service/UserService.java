package com.dev.kept.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dev.kept.Beans.User;
import com.dev.kept.dto.UpdateUserRequest;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    ResponseEntity<?> getUserById(Long id);
    ResponseEntity<?> updateUserById(Long id, User user);
    ResponseEntity<?> updateUserByID(Long id, UpdateUserRequest updateUserRequest);

    String deleteUserById(Long id);

}
