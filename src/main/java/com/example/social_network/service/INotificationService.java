package com.example.social_network.service;

import com.example.social_network.model.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> listNotification();
    void createNotifSender(Long idUser1,Long idUser2);
    void notifReceive(Long idNotif);
    void deleteNotification(Long id);

}
