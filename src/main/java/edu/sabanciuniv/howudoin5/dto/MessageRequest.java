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
    private String username;
    private String password;

    private String friendUsername;
    private String message;


    MessageRequest(String username, String password, String friendUsername) {
        this.username = username;
        this.password = password;
        this.friendUsername = friendUsername;
    }
}
