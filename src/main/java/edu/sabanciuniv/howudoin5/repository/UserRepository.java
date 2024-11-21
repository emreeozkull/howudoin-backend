package edu.sabanciuniv.howudoin5.repository;

import edu.sabanciuniv.howudoin5.models.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    UserEntity getUserEntityByUsername(String username);
}
