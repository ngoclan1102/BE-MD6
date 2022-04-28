package com.example.social_network.service;

import com.example.social_network.model.Role;
import com.example.social_network.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
