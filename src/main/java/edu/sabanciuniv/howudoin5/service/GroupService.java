package edu.sabanciuniv.howudoin5.service;

import edu.sabanciuniv.howudoin5.dto.GroupMessage;
import edu.sabanciuniv.howudoin5.models.GroupEntity;
import edu.sabanciuniv.howudoin5.models.UserEntity;
import edu.sabanciuniv.howudoin5.repository.GroupRepository;
import edu.sabanciuniv.howudoin5.repository.UserRepository;
import edu.sabanciuniv.howudoin5.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {


    @Autowired
    GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    public void create(GroupEntity group) {
        List<String> groupUserNames = group.getUserNames();
        System.out.println("group user names:");
        System.out.println(groupUserNames);

        groupRepository.save(group);
        for (String userName : groupUserNames) {
            UserEntity user = userService.getUserByUsername(userName);
            if (user != null) {
                List<String> groupsIdForUser = user.getGroups();
                groupsIdForUser.add(group.getId());
                user.setGroups(groupsIdForUser);

                System.out.println("user.setGroups " + user.toString());
                userService.userRepository.save(user);
            }
            else {
                System.out.println("user not found:" + userName);
            }

        }
    }

    public void addMemberWithId(String memberUserName, String groupId) {
         GroupEntity group1 = groupRepository.getGroupEntityById(groupId);
         group1.getUserNames().add(memberUserName);
         groupRepository.save(group1);
    }

    public List<String> getALlMembers(String groupId) {
        GroupEntity group1 = groupRepository.getGroupEntityById(groupId);
        return group1.getUserNames();
    }

    public void sendMassage(String groupId, GroupMessage message) {
        GroupEntity group1 = groupRepository.getGroupEntityById(groupId);
        System.out.println("getting group entity: " + group1);
        List<GroupMessage> groupMessages = group1.getGroupMessages();
        groupMessages.add(message);
        //group1.setGroupMessages(groupMessages);
        groupRepository.save(group1);
    }

    public List<GroupMessage> getAllMessages(String groupId) {
        return groupRepository.getGroupEntityById(groupId).getGroupMessages();
    }

    public GroupEntity getGroupById(String groupId) {
        return groupRepository.getGroupEntityById(groupId);
    }
}
