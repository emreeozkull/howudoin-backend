package edu.sabanciuniv.howudoin5.dto;

import edu.sabanciuniv.howudoin5.models.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class FriendsApi {
    public String status;
    public List<UserEntity> friendList;
}
