package edu.sabanciuniv.howudoin5.controller;

import edu.sabanciuniv.howudoin5.models.UserEntity;
import edu.sabanciuniv.howudoin5.security.CustomUserDetailsService;
import edu.sabanciuniv.howudoin5.security.JwtService;
import edu.sabanciuniv.howudoin5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtService jwtService;


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

    @PostMapping("/login")
    public String loginUser(@RequestBody UserEntity user1) {
        String username1 =  user1.getUsername();
        String password1 = user1.getPassword();
        String isLogin =  userService.loginUser(username1, password1);
        return Objects.requireNonNullElse(isLogin, "login failed");
    }

    @PostMapping("/test-jwt")
    public String testJwt(@RequestHeader HttpHeaders header) {
        return Objects.requireNonNull(header.get("Authorization")).get(0).substring(7);
    }

    @PostMapping("/friends/add")
    public String addFriend(@RequestBody String friendUsername, @RequestHeader HttpHeaders header) {

        String token = Objects.requireNonNull(header.get("Authorization")).get(0).substring(7);
        UserEntity userByToken = customUserDetailsService.loadUserByToken(token);

        userService.addFriendRequest(userByToken, friendUsername);
        return userByToken.getUsername() + " friend request to" + friendUsername ;
    }


    @PostMapping("/friends") // change this to get with header authentication
    public List<UserEntity> getFriends(@RequestHeader HttpHeaders header) {

        String token = Objects.requireNonNull(header.get("Authorization")).get(0).substring(7);
        UserEntity user1 = customUserDetailsService.loadUserByToken(token);
        if (user1.getUsername() != null) {
            List<String> userFriends =  user1.getFriends();
            List<UserEntity> friendList = new ArrayList<>();
            for (String friend : userFriends) {friendList.add(userService.getUserByUsername(friend));}
            return friendList;
        }
        else return null;
    }

    @PostMapping("/friends/accept")
    public String acceptFriend(@RequestBody String friendUsername, @RequestHeader HttpHeaders header) {
        UserEntity user1 = customUserDetailsService.loadUserByToken(Objects.requireNonNull(header.get("Authorization")).get(0).substring(7));

        String username = user1.getUsername();
        userService.addFriends(username, friendUsername);
        return "friend " + friendUsername + " accepted by " + username;
    }



}
