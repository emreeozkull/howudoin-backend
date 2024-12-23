package edu.sabanciuniv.howudoin5.repository;

import edu.sabanciuniv.howudoin5.models.GroupEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<GroupEntity, String> {
    GroupEntity getGroupEntityById(String groupId);
}
