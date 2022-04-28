package com.example.social_network.service.impl;

import com.example.social_network.model.Role;
import com.example.social_network.model.RoleName;
import com.example.social_network.ropository.IRoleRepo;
import com.example.social_network.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleRepo iRoleRepo;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return iRoleRepo.findByName(name);
    }


}
