package com.example.social_network.service.impl;

import com.example.social_network.model.Users;
import com.example.social_network.ropository.IUserRepo;
import com.example.social_network.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    IUserRepo iUserRepo;


    @Override
    public Optional<Users> findByUsername(String name) {
        return iUserRepo.findByUsername(name);
    }

    @Override
    public List<Users> findUserByUsername(String name) {
        return iUserRepo.findUserByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return iUserRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return iUserRepo.existsByEmail(email);
    }


    @Override
    public Users save(Users user) {
        return iUserRepo.save(user);
    }

    @Override
    public Users findById(Long id) {
        return iUserRepo.findById(id).get();
    }

    @Override
    public List<Users> getAll() {
        return iUserRepo.findAll();
    }

    @Override
    public Users findUserById(Long id) {
        return iUserRepo.findById(id).get();
    }


}
