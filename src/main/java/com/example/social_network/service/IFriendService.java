package com.example.social_network.service;

import com.example.social_network.model.Friend;
import com.example.social_network.model.Users;

import java.util.List;

public interface IFriendService {
    List<Friend> findAll();
    List<Users> getListNotAddFriend(Long idUser1);
    List<Users> getListAddedFriend(Long idUser1);
    List<Users> getListWaitMakeFriend(Long idUser1);
    List<Users> listMutualFriend(Long idUser1,Long idUser2);
    void save(Long idUser1, Long idUser2);
    void setFriend(Long idFriend);
    void deleteAddedFriend(Long idUser,Long idFriend);
    void deleteWaitAddFriend(Long idUser,Long idFriend);
    Friend findById(Long id);
}
