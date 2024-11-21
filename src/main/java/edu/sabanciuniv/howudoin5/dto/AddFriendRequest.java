
package edu.sabanciuniv.howudoin5.dto;

import edu.sabanciuniv.howudoin5.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFriendRequest {
    private UserEntity user;
    private String friendUsername;


    // made all by lombok

    // Constructors
    // public AddFriendRequest() {}

//    public AddFriendRequest(UserEntity user, String friendUsername) {
//        this.user = user;
//        this.friendUsername = friendUsername;
//    }

//    // Getters and Setters
//    public UserEntity getUser() {
//        return user;
//    }
//
//    public void setUser(UserEntity user) {
//        this.user = user;
//    }
//
//    public String getFriendUsername() {
//        return friendUsername;
//    }
//
//    public void setFriendUsername(String friendUsername) {
//        this.friendUsername = friendUsername;
//    }

}
