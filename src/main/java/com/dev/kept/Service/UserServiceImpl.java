package com.dev.kept.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.kept.Beans.User;
import com.dev.kept.Repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repo;
    
    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User saveUser(User user){
        return repo.save(user);
    }

    @Override
    public List<User> getAllUsers(){
        return repo.findAll();
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        return  repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> updateUserById(Long id, User user) {
        
        User updatedUser = repo.findById(id).get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        repo.save(updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public String deleteUserById(Long id) {
        repo.deleteById(id);
        return "Deleted Successfully";
    }

    
    

    
}
