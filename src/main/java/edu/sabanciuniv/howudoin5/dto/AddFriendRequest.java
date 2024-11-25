
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

}
