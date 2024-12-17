package edu.sabanciuniv.howudoin5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageRequest {

    private String friendID;
    private String message;


    MessageRequest(String friendId) {

        this.friendID = friendId;
    }
}
