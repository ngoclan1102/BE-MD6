package com.example.social_network.ropository;

import com.example.social_network.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepo extends JpaRepository<Notification, Long> {
}
