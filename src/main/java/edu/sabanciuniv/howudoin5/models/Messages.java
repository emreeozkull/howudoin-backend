package edu.sabanciuniv.howudoin5.models;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class Messages {

    private int senderId;
    private int receiverId;

    private String messages;

    public Messages(int senderId, int receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messages = message;
    }

}
