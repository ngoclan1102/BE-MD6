package com.example.social_network.model;

import lombok.Data;

import javax.persistence.Entity;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class CheckDate {
    public static LocalDateTime getTimePost() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        return today;
    }

}
