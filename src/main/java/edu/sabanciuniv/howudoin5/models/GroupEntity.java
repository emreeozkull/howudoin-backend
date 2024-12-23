package edu.sabanciuniv.howudoin5.models;

import edu.sabanciuniv.howudoin5.dto.GroupMessage;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GroupEntity {

    private String id;
    public String GroupName;
    private List<String> userNames;
    private List<GroupMessage> groupMessages;

}
