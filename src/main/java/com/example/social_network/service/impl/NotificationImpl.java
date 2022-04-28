package com.example.social_network.service.impl;

import com.example.social_network.model.Notification;
import com.example.social_network.model.Users;
import com.example.social_network.ropository.INotificationRepo;
import com.example.social_network.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationImpl implements INotificationService {
    @Autowired
    INotificationRepo notificationRepo;

    @Autowired
    IUserServiceImpl userService;

    @Override
    public List<Notification> listNotification() {
        return notificationRepo.findAll();
    }

    @Override
    public void createNotifSender(Long idUser1,Long idUser2) {
        String nameSender = "";
        for (Users u: userService.getAll()) {
            if (u.getId() == idUser1){
                nameSender = u.getName();
            }
        }
        Notification notification = new Notification(LocalDateTime.now(),"Bạn có lời mời kết bạn từ " + nameSender, userService.findById(idUser1), userService.findById(idUser2));
        notificationRepo.save(notification);
    }


  @Override
    public void notifReceive(Long idNotif) {
        String nameReceive = "";
        Notification notification = new Notification();
      for (Notification no:listNotification()) {
          if (no.getId() == idNotif){
              nameReceive = no.getTo().getName();
              notification.setSince(LocalDateTime.now());
              notification.setContent(nameReceive+ " đã chấp nhận lời mời kết bạn của bạn " );
          }
      }
        notificationRepo.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepo.deleteById(id);
    }
}
