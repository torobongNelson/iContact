package com.praisebassey.iContact.Controller;

import com.praisebassey.iContact.Dtos.Requests.ContactRequest;
import com.praisebassey.iContact.Model.Contact;
import com.praisebassey.iContact.Service.iContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final iContactService contactService;

    public ContactController(iContactService contactService) {
        this.contactService = contactService;
    }


        @GetMapping("/contacts")
        public ResponseEntity<List<Contact>> viewContacts(){
            List<Contact> contacts = contactService.viewContactsAlphabetically();
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }



        @GetMapping("/{id}")
        public ResponseEntity<Contact> viewContact(@PathVariable("id") String id){
            Contact contact = contactService.viewContactById(id);
            return new ResponseEntity<>(contact,HttpStatus.OK);
        }

        @GetMapping("/firstName") // /api/contact/firstName?firstName=Nelson
        public ResponseEntity<List<Contact>>  getContactByFirstName(@RequestParam("firstName") String firstName){
          List<Contact> names =   contactService.viewContactByFirstName(firstName);
          return new ResponseEntity<>(names,HttpStatus.OK);
        }


        @PostMapping("/create")
        public ResponseEntity<Contact> createContact(@RequestBody ContactRequest request){
            Contact contact = contactService.createContact(request);
            return new ResponseEntity<>(contact,HttpStatus.CREATED);
        }


        @PutMapping("/update")
        public ResponseEntity<Contact> updateContact(@RequestBody Contact contact){
            Contact updatedContact = contactService.updateContact(contact);
            return new ResponseEntity<>(updatedContact,HttpStatus.CREATED);
        }

        @DeleteMapping ("/delete/{id}")
        public ResponseEntity<String> deleteContact(@PathVariable("id") String id ){
            String deleteContact = contactService.deleteContact(id);
            return new ResponseEntity<>(deleteContact,HttpStatus.OK);
        }

    }


