package com.example.social_network.security.userprincal;

import com.example.social_network.model.Users;
import com.example.social_network.ropository.IUserRepo;
import com.example.social_network.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    IUserService iUserService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = iUserRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found -> username or pass" + username));
        return UserPrinciple.build(user);
    }

    //HAM LAY RA USER HIEN TAI DE THUC HIEN THAO TAC VOI DB
    public Users getCurrentUser(){
        Optional<Users> user;
        String userName;
        //Lay 1 object principal trong SecurityContexHolder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //So sanh obj voi Userdetails neu ma dung thi gan userName = principal.getUsername();
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        } else {
            //neu khong phai user hien tai thi userName = principal.toString();
            userName = principal.toString();
        }
        //kiem tra neu userName ton tai trong DB thi gan user = ham tim kiem trong DB theo userName do
        if(iUserRepo.existsByUsername(userName)){
            user = iUserRepo.findByUsername(userName);
        } else {
            //Neu chua ton tai thi tra ve 1 the hien cua lop User thong qua Optional.of
            user = Optional.of(new Users());
            //set cho no 1 cai ten user an danh Day la truong hop ma tuong tac qua dang nhap kieu FB hay GG
            user.get().setUsername("Anonymous");
        }
        return user.get();
    }
}
