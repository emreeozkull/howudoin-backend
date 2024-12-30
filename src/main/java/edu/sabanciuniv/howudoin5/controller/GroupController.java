package edu.sabanciuniv.howudoin5.controller;


import edu.sabanciuniv.howudoin5.dto.GroupMessage;
import edu.sabanciuniv.howudoin5.models.GroupEntity;
import edu.sabanciuniv.howudoin5.models.UserEntity;
import edu.sabanciuniv.howudoin5.security.CustomUserDetailsService;
import edu.sabanciuniv.howudoin5.service.GroupService;
import edu.sabanciuniv.howudoin5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    GroupService groupService;

    @GetMapping()
    public ResponseEntity<List<GroupEntity>> getAllGroups(@RequestHeader HttpHeaders header) {
        System.out.println("getAllGroups");
        String token = Objects.requireNonNull(header.get("Authorization")).get(0).substring(7);
        UserEntity userByToken = customUserDetailsService.loadUserByToken(token);
        System.out.println(userByToken);

        List<String> groupsId = new ArrayList<>();
        groupsId = userByToken.getGroups();
        List<GroupEntity> groups = new ArrayList<>();
        for (String groupId : groupsId) {
            groups.add(groupService.getGroupById(groupId));
        }
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/create")
    public String createGroup(@RequestBody GroupEntity group, @RequestHeader HttpHeaders header) {
        System.out.println("createGroup");
        System.out.println(group);

        String groupNames = group.getGroupName();
        List<String> userNames = new ArrayList<String>(); //= group.getUserNames();

        String token = Objects.requireNonNull(header.get("Authorization")).get(0).substring(7);
        UserEntity userByToken = customUserDetailsService.loadUserByToken(token);

        userNames.add(userByToken.getUsername());
        // check if user entered it's friends
        for (String userName :group.getUserNames()){
            if (userByToken.getFriends().contains(userName)){
                userNames.add(userName);
            }
        }

        GroupEntity createdGroup = new GroupEntity();
        createdGroup.setGroupName(groupNames);
        createdGroup.setUserNames(userNames);

        List<GroupMessage> groupMessages = new ArrayList<GroupMessage>();
        createdGroup.setGroupMessages(groupMessages);
        System.out.println("group creation localdate now: " + LocalDate.now());
        createdGroup.creationDate = LocalDate.now();
        groupService.create(createdGroup);
        return "Successful Group named "+ group.GroupName + " created";
    }

    @PostMapping("/{groupId}/send")
    public String sendMessage(@PathVariable String groupId, @RequestBody GroupMessage message, @RequestHeader HttpHeaders header) {

        String token = Objects.requireNonNull(header.get("Authorization")).get(0).substring(7);
        String usernameByToken = customUserDetailsService.loadUserByToken(token).getUsername();
        message.setSender(usernameByToken);
        groupService.sendMassage(groupId,message);

        return "Message sent";
    }

    @PostMapping("/{groupId}/add-member")
    public String addMember(@RequestBody String memberUserName, @PathVariable String groupId) {
        groupService.addMemberWithId(memberUserName,groupId);
        return "Added member to group "+groupId;
    }


    @GetMapping("/{groupId}/members")
    public List<String> getMembers(@PathVariable String groupId) {
        return groupService.getALlMembers(groupId);
    }

    @GetMapping("/{groupId}/messages")
    public List<GroupMessage> getMessages(@PathVariable String groupId) {
        return groupService.getAllMessages(groupId);
    }

    @GetMapping("/{groupId}/getGroupDetails")
    public ResponseEntity<GroupEntity> getGroupDetails(@PathVariable String groupId) {

        //List<GroupMessage> groupMessages = groupService.getAllMessages(groupId); Null ise sorun oluyor
        GroupEntity group = groupService.getGroupById(groupId);
        System.out.println(group);
        return ResponseEntity.ok(group);
    }

}
