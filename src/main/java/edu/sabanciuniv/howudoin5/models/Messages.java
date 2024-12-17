package edu.sabanciuniv.howudoin5.models;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class Messages {

    private String senderId;
    private String receiverId;

    private String messages;

    public Messages(String senderId, String receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messages = message;
    }

}
