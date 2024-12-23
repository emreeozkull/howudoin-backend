package edu.sabanciuniv.howudoin5.service;

import edu.sabanciuniv.howudoin5.models.Messages;
import edu.sabanciuniv.howudoin5.repository.MassagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MessageService {

    @Autowired
    MassagesRepository massagesRepository;

    public void createMessage(Messages message) {massagesRepository.save(message);}

    public Messages getMassageByIDs(String id1, String id2) {

        List<Messages> massList =  massagesRepository.findAll();
        for (Messages m : massList) {
            if (m.getSenderId() == id1 && m.getReceiverId() == id2) {return m;}
        }
        return null;
    }

//    public void appendMassage(String id1, String id2, String content) {
//
//        Messages mess = new Messages(id1,id2,content);
//        massagesRepository.save(mess);
//    }

    public List<Messages> getConversationHistory(String id1, String id2) {
        List<Messages> conversation = new ArrayList<Messages>();
        List<Messages> massList =  massagesRepository.findAll();
        for (Messages m : massList) {
            if ((Objects.equals(m.getSenderId(), id1) && Objects.equals(m.getReceiverId(), id2))|| Objects.equals(m.getReceiverId(), id1) && Objects.equals(m.getSenderId(), id2)){
                conversation.add(m);
            }
        }
        return conversation;
    }

}
