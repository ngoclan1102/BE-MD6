package com.example.social_network.dto.respon;

import com.example.social_network.model.Users;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String name;
    private Users users;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse() {
    }

    public JwtResponse(String token, String type, String name, Collection<? extends GrantedAuthority> roles) {

        this.token = token;
        this.type = type;
        this.name = name;
        this.roles = roles;
    }

    public JwtResponse(Long id,String token, String name, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.roles = authorities;
    }

    public JwtResponse(Long id, String token, String type, String name, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.name = name;
        this.roles = roles;
    }

    public JwtResponse(String token, Users users) {
        this.token = token;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
