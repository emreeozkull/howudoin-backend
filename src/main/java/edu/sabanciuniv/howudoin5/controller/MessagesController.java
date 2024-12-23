package edu.sabanciuniv.howudoin5.controller;

import edu.sabanciuniv.howudoin5.dto.MessageHistoryApi;
import edu.sabanciuniv.howudoin5.dto.MessageRequest;
import edu.sabanciuniv.howudoin5.models.Messages;
import edu.sabanciuniv.howudoin5.models.UserEntity;
import edu.sabanciuniv.howudoin5.security.CustomUserDetailsService;
import edu.sabanciuniv.howudoin5.service.MessageService;
import edu.sabanciuniv.howudoin5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    //-	POST /messages/send: Send a message to a friend
    //-	GET /messages: Retrieve conversation history

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping()
    public String test() {
        return "test";
    }

    @PostMapping() // change this to get mapping after auth imp.
    public List<Messages> getConversationHist(@RequestBody MessageHistoryApi messageRequest, @RequestHeader HttpHeaders header ) {

        String token = Objects.requireNonNull(header.get("Authorization")).get(0).substring(7);
        UserEntity userByToken = customUserDetailsService.loadUserByToken(token);

        //userService.addFriendRequest(userByToken, friendUsername);
        String id1 = userByToken.getId();
        String id2 = messageRequest.getFriendID();

        return messageService.getConversationHistory(id1,id2);

    }

    @PostMapping("/send")
    public String send(@RequestBody MessageRequest messageRequest,@RequestHeader HttpHeaders header) {
        String token = Objects.requireNonNull(header.get("Authorization")).get(0).substring(7);
        UserEntity userByToken = customUserDetailsService.loadUserByToken(token);

        String senderId = userByToken.getId();
        String friendId =  messageRequest.getFriendID();

        String username = userByToken.getUsername();

        String friendUserName = userService.getUserById(friendId).getUsername();


        String message = messageRequest.getMessage();

            if (userService.checkFriendsByUsername(username,friendUserName)) {
                String receiverId = userService.getUserByUsername(friendUserName).getId();
                    Messages massage1 = new Messages(senderId,receiverId,message);
                    messageService.createMessage(massage1);
                return "message sent to: " + receiverId ;

            }else return "there is no friend named " + friendUserName +" with " + username;


            //else return "invalid username or password";

    }


}
