package edu.sabanciuniv.howudoin5.controller;

import edu.sabanciuniv.howudoin5.dto.MessageRequest;
import edu.sabanciuniv.howudoin5.models.Messages;
import edu.sabanciuniv.howudoin5.models.UserEntity;
import edu.sabanciuniv.howudoin5.service.MessageService;
import edu.sabanciuniv.howudoin5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    //-	POST /messages/send: Send a message to a friend
    //-	GET /messages: Retrieve conversation history

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @GetMapping()
    public String test() {
        return "test";
    }

    @PostMapping() // change this to get mapping after auth imp.
    public List<Messages> getConversationHist(@RequestBody MessageRequest messageRequest ) {
        int id1,id2;
        if (!userService.loginUser(messageRequest.getUsername(), messageRequest.getPassword())) {
            return new ArrayList<>();
        } else {
            String username = messageRequest.getUsername();
            String friendUsername = messageRequest.getFriendUsername();
            id1 = userService.getUserByUsername(username).getId();
            id2 = userService.getUserByUsername(friendUsername).getId();

        }
        //int senderId = id;
        return messageService.getConversationHistory(id1,id2);

    }

    @PostMapping("/send")
    public String send(@RequestBody MessageRequest messageRequest) {
        String username = messageRequest.getUsername();
        String password = messageRequest.getPassword();

        String friendUserName =  messageRequest.getFriendUsername();

        String message = messageRequest.getMessage();

        if (userService.loginUser(username, password)){
            UserEntity user1 =  userService.getUserByUsername(username);
            int senderId = user1.getId();

            if (userService.checkFriendsByUsername(username,friendUserName)) {
                int receiverId = userService.getUserByUsername(friendUserName).getId();
                if (messageService.getMassageByIDs(senderId,receiverId) != null) {
                    messageService.appendMassage(senderId,receiverId,message);
                }else {
                    Messages massage1 = new Messages(senderId,receiverId,message);
                    messageService.createMessage(massage1);
                }
                return "message sent to: " + receiverId ;

            }else return "there is no friend named " + friendUserName +" with " + username;

        }else return "invalid username or password";

    }


}
