package edu.sabanciuniv.howudoin5.service;

import edu.sabanciuniv.howudoin5.models.UserEntity;
import edu.sabanciuniv.howudoin5.repository.UserRepository;
import edu.sabanciuniv.howudoin5.security.JwtService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;
    
    
    public void createUser(UserEntity user1) {
        userRepository.save(user1);
    }

    public String loginUser(String username, String password) {
        UserEntity user1 ;
        user1 = userRepository.getUserEntityByUsername(username);
        if (user1 == null) {
            return "false";
        }
        else if (!user1.getPassword().equals(password)) {
            return "false";
        }else
        {
            return jwtService.generateToken(user1);
        }
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.getUserEntityByUsername(username);
    }

    public UserEntity getUserById(String id) {
        return userRepository.getUserEntityById(id);
    }

    public void addFriendRequest(UserEntity user1, String friendUsername) {
        String username = user1.getUsername();
        UserEntity user2 = userRepository.getUserEntityByUsername(friendUsername);

//        user2.getFriend_requests().add(username);
        user2.addFriendRequest(username);
        userRepository.save(user2);
    }

    public void addFriends(String username1, String username2) {
        UserEntity user1 = getUserByUsername(username1);
        UserEntity user2 = getUserByUsername(username2);

        user1.getFriends().add(username2);
        user1.getFriend_requests().remove(username2);
        userRepository.save(user1);

        user2.getFriends().add(username1);
        userRepository.save(user2);
    }


    public boolean checkFriendsByUsername(String username,String friendUsername) {

        UserEntity user1 = userRepository.getUserEntityByUsername(username);
        UserEntity user2 = userRepository.getUserEntityByUsername(friendUsername);

        if (userRepository.getUserEntityByUsername(username) == null || userRepository.getUserEntityByUsername(friendUsername) == null) {
            return false;
        }else{

            for (String userName :  user1.getFriends()) {
                if (userName.equals(friendUsername)) {return true;}
            }
            return false;
        }
    }

    public List<String> getAllFriendRequests(UserEntity user1) {
        return user1.getFriend_requests();
    }
}
