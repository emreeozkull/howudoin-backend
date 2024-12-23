package edu.sabanciuniv.howudoin5.models;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
public class Messages {

    public String senderId;
    public String receiverId;

    public String messages;

    public Messages(String senderId, String receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messages = message;
    }

}
