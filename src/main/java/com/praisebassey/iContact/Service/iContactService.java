package com.praisebassey.iContact.Service;

import com.praisebassey.iContact.Dtos.Requests.ContactRequest;
import com.praisebassey.iContact.Interface.iContactServiceInterface;
import com.praisebassey.iContact.Model.Contact;
import com.praisebassey.iContact.Repository.iContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class iContactService implements iContactServiceInterface {
    private final iContactRepository contactRepository;

    public iContactService(iContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public Contact createContact(ContactRequest request){
        if(contactRepository.existsByPhone(request.getPhone())){
           throw new RuntimeException("This contact already exist");
        }

        Contact contact = new Contact(request.getFirstName(),request.getLastName(),request.getPhone());

        return contactRepository.save(contact);

    }


    @Override
    public Contact viewContactById(String id) {
        return contactRepository.findById(id).orElseThrow(()-> new RuntimeException("Contact not found"));
    }

    @Override
    public List<Contact> viewContactByFirstName(String firstName) {
        return contactRepository.findByFirstName(firstName);
    }

    @Override
    public List<Contact> viewContactsAlphabetically() {
        return contactRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Contact updateContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public String deleteContact(String id) {
        contactRepository.deleteById(id);
        return "Contact Deleted!";
    }
}
