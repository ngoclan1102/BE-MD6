package com.example.social_network.controller;

import com.example.social_network.dto.request.SignInForm;
import com.example.social_network.dto.request.SignUpForm;
import com.example.social_network.dto.respon.JwtResponse;
import com.example.social_network.dto.respon.ResponMess;
import com.example.social_network.model.Role;
import com.example.social_network.model.RoleName;
import com.example.social_network.model.Users;
import com.example.social_network.security.jwt.JwtProvider;
import com.example.social_network.security.userprincal.UserPrinciple;
import com.example.social_network.service.IRoleService;
import com.example.social_network.service.IUserService;
import com.example.social_network.service.impl.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping()
public class AuthController {

    @Autowired
    IUserServiceImpl iUserService;

    @Autowired
    IRoleService iRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (iUserService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponMess("The username existed! Please try again!"), HttpStatus.OK);
        }
        if(iUserService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponMess("The email existed! Please try again!"), HttpStatus.OK);
        }
        Users user = new Users(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRole = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRole.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = iRoleService.findByName(RoleName.ROLE_ADMIN).orElseThrow(
                            ()-> new RuntimeException("Role not found ")
                    );
                       roles.add(adminRole);
                       break;
                case "pm":
                    Role pmRole = iRoleService.findByName(RoleName.ROLE_PM).orElseThrow(
                            () -> new RuntimeException(" Role pm not found")
                    );
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = iRoleService.findByName(RoleName.ROLE_USER).orElseThrow(
                            () -> new RuntimeException(" Role user not found")
                    );
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        iUserService.save(user);
        return new ResponseEntity<>(new ResponMess("yes"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login (@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        System.out.println("userPrinciple");
        System.out.println(userPrinciple);
        Users users = iUserService.findByUsername(userPrinciple.getUsername()).get();
//        trả kèm theo id;
        return ResponseEntity.ok( new JwtResponse(userPrinciple.getId(), token, userPrinciple.getName(), userPrinciple.getAuthorities()));
//        return ResponseEntity.ok( new JwtResponse(token, users));
    }
}
