package edu.sabanciuniv.howudoin5.repository;

import edu.sabanciuniv.howudoin5.models.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MassagesRepository extends MongoRepository<Messages, String> {

}
