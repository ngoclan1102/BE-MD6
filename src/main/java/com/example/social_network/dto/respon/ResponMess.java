package com.example.social_network.dto.respon;

public class ResponMess {
    private String message;

    public ResponMess() {
    }

    public ResponMess(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
