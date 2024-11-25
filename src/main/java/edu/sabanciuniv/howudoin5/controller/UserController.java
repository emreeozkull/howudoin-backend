package edu.sabanciuniv.howudoin5.controller;

import edu.sabanciuniv.howudoin5.dto.AddFriendRequest;
import edu.sabanciuniv.howudoin5.dto.AuthRequest;
import edu.sabanciuniv.howudoin5.models.UserEntity;
import edu.sabanciuniv.howudoin5.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/get-all-users")
    public String getAllUsers() {
        return userService.getAllUsers().toString();
    }

    @GetMapping("/get-test")
    public String getTest() {
        return "get test";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserEntity user1) {
        userService.createUser(user1);
        return ("user registered");
    }

    @GetMapping ("/register/{name}")
    public String registerUser(@PathVariable String name) {
        UserEntity user1  = new UserEntity(name,"12eed");

        userService.createUser(user1);
        return ("user registered");
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody UserEntity user1) {
        String username1 =  user1.getUsername();
        String password1 = user1.getPassword();
        String isLogin =  userService.loginUser(username1, password1);
        return Objects.requireNonNullElse(isLogin, "login failed");

    }

    @PostMapping("/friends/add")
    public String addFriend(@RequestBody AddFriendRequest addFriendRequest) {

        UserEntity user1 = addFriendRequest.getUser();
        String friendUsername = addFriendRequest.getFriendUsername();

        userService.addFriendRequest(user1, friendUsername);
        return "friend request added";
    }


    @PostMapping("/friends") // change this to get with header authentication
    public List<UserEntity> getFriends(@RequestBody AuthRequest authRequest) {
        String username =  authRequest.getUsername();
        String password =  authRequest.getPassword();

        UserEntity user1 = userService.getUserByUsername(username);
        if (user1.getPassword().equals(password)) {
            List<String> userFriends =  user1.getFriends();
            List<UserEntity> friendList = new ArrayList<>();
            for (String friend : userFriends) {friendList.add(userService.getUserByUsername(friend));}
            return friendList;
        }
        else return null;
    }

    @PostMapping("/friends/accept")
    public String acceptFriend(@RequestBody AddFriendRequest addFriendRequest) {
        UserEntity user1 = addFriendRequest.getUser();
        String friendUsername = addFriendRequest.getFriendUsername();
        String username = user1.getUsername();
        userService.addFriends(username, friendUsername);
        return "friend" + friendUsername + " accepted";
    }



}
