package com.example.social_network.ropository;

import com.example.social_network.model.Role;
import com.example.social_network.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
