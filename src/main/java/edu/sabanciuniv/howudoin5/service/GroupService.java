package edu.sabanciuniv.howudoin5.service;

import edu.sabanciuniv.howudoin5.dto.GroupMessage;
import edu.sabanciuniv.howudoin5.models.GroupEntity;
import edu.sabanciuniv.howudoin5.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public void create(GroupEntity group) {
        groupRepository.save(group);
    }

    public void addMemberWithId(String memberUserName, Long groupId) {
         GroupEntity group1 = groupRepository.getGroupEntityById(groupId);
         group1.getUserNames().add(memberUserName);
         groupRepository.save(group1);
    }

    public List<String> getALlMembers(Long groupId) {
        GroupEntity group1 = groupRepository.getGroupEntityById(groupId);
        return group1.getUserNames();
    }

    public void sendMassage(Long groupId, GroupMessage message) {
        GroupEntity group1 = groupRepository.getGroupEntityById(groupId);
        List<GroupMessage> groupMessages = group1.getGroupMessages();
        groupMessages.add(message);
        //group1.setGroupMessages(groupMessages);
        groupRepository.save(group1);
    }

    public List<GroupMessage> getAllMessages(Long groupId) {
        return groupRepository.getGroupEntityById(groupId).getGroupMessages();
    }
}
