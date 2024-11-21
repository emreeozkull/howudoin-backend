package edu.sabanciuniv.howudoin5.controller;


import edu.sabanciuniv.howudoin5.dto.GroupMessage;
import edu.sabanciuniv.howudoin5.models.GroupEntity;
import edu.sabanciuniv.howudoin5.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping("/create")
    public String createGroup(@RequestBody GroupEntity group) {
        groupService.create(group);
        return "Group named "+ group.GroupNames + " created";
    }

    @PostMapping("{groupId}/send")
    public String sendMessage(@PathVariable Long groupId, @RequestBody GroupMessage message) {
        groupService.sendMassage(groupId,message);
        return "Message sent";
    }

    @PostMapping("/{groupId}/add-member")
    public String addMember(@RequestBody String memberUserName, @PathVariable Long groupId) {
        groupService.addMemberWithId(memberUserName,groupId);
        return "Added member to group "+groupId;
    }


    @GetMapping("/{groupId}/members")
    public List<String> getMembers(@PathVariable Long groupId) {
        return groupService.getALlMembers(groupId);
    }

    @GetMapping("/{groupId}/messages")
    public List<GroupMessage> getMessages(@PathVariable Long groupId) {
        return groupService.getAllMessages(groupId);
    }

}
