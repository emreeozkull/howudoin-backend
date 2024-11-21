package edu.sabanciuniv.howudoin5.service;

import edu.sabanciuniv.howudoin5.models.Messages;
import edu.sabanciuniv.howudoin5.repository.MassagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MassagesRepository massagesRepository;

    public void createMessage(Messages message) {massagesRepository.save(message);}

    public Messages getMassageByIDs(int id1, int id2) {

        List<Messages> massList =  massagesRepository.findAll();
        for (Messages m : massList) {
            if (m.getSenderId() == id1 && m.getReceiverId() == id2) {return m;}
        }
        return null;
    }

    public void appendMassage(int id1, int id2, String content) {
//        Messages m1 = getMassageByIDs(id1,id2);
//        m1.getMessages().add(content);
        //massagesRepository.save(m1);

        Messages mess = new Messages(id1,id2,content);
        massagesRepository.save(mess);
    }

    public List<Messages> getConversationHistory(int id1, int id2) {
        List<Messages> conversation = new ArrayList<Messages>();
        List<Messages> massList =  massagesRepository.findAll();
        for (Messages m : massList) {
            if ((m.getSenderId() == id1 && m.getReceiverId() == id2 )|| m.getReceiverId() == id1 && m.getSenderId() == id2){
                conversation.add(m);
            }
        }
        return conversation;
    }

}
