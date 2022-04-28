package com.example.social_network.service.impl;

import com.example.social_network.model.Friend;
import com.example.social_network.model.Users;
import com.example.social_network.ropository.FriendRepo;
import com.example.social_network.ropository.IUserRepo;
import com.example.social_network.service.IFriendService;
import com.example.social_network.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements IFriendService {
    @Autowired
    FriendRepo friendRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IUserService userService;

    @Override
    public List<Friend> findAll() {
        return friendRepo.findAll();
    }

    @Override
    public List<Users> getListAddedFriend(Long idUser) {
        return userRepo.listAddedFriend(idUser);
    }

    @Override
    public List<Users> getListNotAddFriend(Long idUser1) {
        List<Users> userList = userService.getAll();
        List<Friend> getList = friendRepo.listNotAddFriend(idUser1);
        List<Long> getId = new ArrayList<>();
        getId.add(idUser1);
        for (int i = 0; i < getList.size(); i++) {
            if (getList.get(i).getUser1().getId() == idUser1) {
                getId.add(getList.get(i).getUser2().getId());
            }
            if (getList.get(i).getUser2().getId() == idUser1) {
                getId.add(getList.get(i).getUser1().getId());
            }
        }
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < getId.size(); j++) {
                if (userList.get(i).getId() == getId.get(j)) {
                    userList.remove(i);
                }
            }
        }
        return userList;
    }

    @Override
    public List<Friend> getListWaitMakeFriend(Long idUser) {
        return userRepo.listWaitMakeFriend(idUser);
    }

    @Override
    public List<Users> listMutualFriend(Long idUser1, Long idUser2) {
        return userRepo.listMutualFriend(idUser1, idUser2);
    }

    @Override
    public void save(Long idUser1, Long idUser2) {
        Friend friend = new Friend(false, LocalDateTime.now(), userService.findUserById(idUser1), userService.findUserById(idUser2));
        friendRepo.save(friend);
    }

     @Override
    public void setFriend(Long idFriend) {
        Friend friend = friendRepo.findById(idFriend).get();
        friend.setStatus(true);

        friendRepo.save(friend);
    }

    @Override
    public void deleteAddedFriend(Long idUser,Long idFriend) {
        friendRepo.delete(friendRepo.findAddedFriendById(idUser,idFriend));
    }

    @Override
    public void deleteWaitAddFriend(Long idUser,Long idFriend) {
        friendRepo.delete(friendRepo.findWaitAddFriendById(idUser, idFriend));
    }

    @Override
    public Friend findById(Long id) {
        return findById(id);
    }

}
