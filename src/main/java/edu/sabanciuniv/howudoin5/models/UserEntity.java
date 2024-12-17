package edu.sabanciuniv.howudoin5.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String password;

    public UserEntity(String name, String s) {
        this.id = getId();
        this.username = name;
        this.password = s;
        this.groups = new ArrayList<Integer>();
        this.friends = new ArrayList<String>();
        this.friend_requests = new ArrayList<String>();
    }

    List<Integer> groups ;
    List<String> friends;
    List<String> friend_requests;

    public void addFriendRequest(String requestUsername) {
        this.friend_requests.add(requestUsername);
    }

}
