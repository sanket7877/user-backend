package com.example.userbackend.controller;

import com.example.userbackend.exception.ResourceNotFoundException;
import com.example.userbackend.model.User;
import com.example.userbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/user")
    public User creatUser(@RequestBody User user){
        return userRepository.save(user);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user=userRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("User Not found with id "+id));

        return ResponseEntity.ok(user);

    }

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User userDetails){
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with this id"+id));
        user.setFirstname(userDetails.getFirstname());
        user.setLastname(userDetails.getLastname());
        user.setUsername(userDetails.getUsername());
        user.setDob(userDetails.getDob());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());

        User updatedUser=userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity <Map< String, Boolean >> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + id));

        userRepository.delete(user);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
