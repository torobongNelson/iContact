package com.praisebassey.iContact.Interface;

import com.praisebassey.iContact.Dtos.Requests.ContactRequest;
import com.praisebassey.iContact.Model.Contact;

import java.util.List;

public interface iContactServiceInterface {
    Contact createContact(ContactRequest request);
    Contact viewContactById(String id );
    List<Contact> viewContactByFirstName(String name );
    List<Contact> viewContactsAlphabetically();
    Contact updateContact(Contact contact);
    String deleteContact(String id);


}
