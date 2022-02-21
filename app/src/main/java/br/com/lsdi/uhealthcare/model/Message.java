package br.com.lsdi.uhealthcare.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Message {

    private long id;

    private String message;

    private LocalDateTime createdAt;

    private User sender;

    public Message(long id, String message, LocalDateTime createdAt, User sender){
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.sender = sender;
    }
}
