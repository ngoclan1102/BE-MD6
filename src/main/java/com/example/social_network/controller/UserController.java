package com.example.social_network.controller;

import com.example.social_network.model.Users;
import com.example.social_network.service.impl.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserServiceImpl iUserService;


    @GetMapping("/showUserDetails/{id}")
    public ResponseEntity<Users> showDetails(@PathVariable Long id){
        return new ResponseEntity<>(iUserService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/editUser")
    public ResponseEntity<Users> editUser(@RequestBody Users users){
        iUserService.save(users);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id) {
        return new ResponseEntity<>(iUserService.findUserById(id) , HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Users>> getAll(){
        List<Users> usersList = iUserService.getAll();
        return new ResponseEntity<>(usersList,HttpStatus.OK);
    }

}
