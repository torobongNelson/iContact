package com.praisebassey.iContact.Repository;

import com.praisebassey.iContact.Model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iContactRepository extends MongoRepository<Contact,String> {
    boolean existsByPhone (String phone);
    List<Contact> findAllByOrderByFirstNameAsc();
    List<Contact> findByFirstName(String firstName);
}
