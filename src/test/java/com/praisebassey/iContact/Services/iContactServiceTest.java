package com.praisebassey.iContact.Services;

import com.praisebassey.iContact.Dtos.Requests.ContactRequest;
import com.praisebassey.iContact.Interface.iContactServiceInterface;
import com.praisebassey.iContact.Model.Contact;
import com.praisebassey.iContact.Repository.iContactRepository;
import com.praisebassey.iContact.Service.iContactService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class iContactServiceTest {

    @Mock
    private iContactRepository contactRepository;

    @Mock
    private iContactService contactService;

    private ContactRequest request;

    private iContactServiceInterface contactServiceInterface;
    private AutoCloseable autoCloseable;
    private Contact contact;


    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        contactServiceInterface = new iContactService(contactRepository);
        request = new ContactRequest("67383966335dcf","Shola","Okpeyemi","07033447823");

        contact = new Contact(request.getId(),request.getFirstName(),request.getLastName(),request.getPhone());

    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void createContact(){
        mock(Contact.class);
        mock(iContactRepository.class);

        when(contactRepository.save(contact)).thenReturn(contact);
        assertThat(contactServiceInterface.createContact(request)).isEqualTo(contact);
    }


    @Test
    void viewContactByIdTest(){
        mock(iContactRepository.class);
        when(contactRepository.findById(request.getId())).thenReturn(Optional.ofNullable( contact));
        assertThat(contactServiceInterface.viewContactById(request.getId())).isEqualTo(contact);
    }


    @Test
    void viewContactByFirstNameTest(){
        mock(iContactRepository.class);
        when(contactRepository.findByFirstName(request.getFirstName())).thenReturn( new ArrayList<Contact>( Collections.singleton(contact)));
        assertThat(contactServiceInterface.viewContactByFirstName(request.getFirstName())).isEqualTo(contact);
    }

    @Test
    void viewContactsAlphabeticallyTest(){
        mock(iContactRepository.class);
        when(contactRepository.findAllByOrderByFirstNameAsc()).thenReturn( new ArrayList<Contact>( Collections.singleton(contact)));
        assertThat(contactServiceInterface.viewContactsAlphabetically().get(0).getFirstName()).isEqualTo(contact.getFirstName());
    }

    @Test
    void updateContactTest(){
        mock(iContactRepository.class);
        when(contactRepository.save(contact)).thenReturn(contact);
        assertThat(contactServiceInterface.updateContact(contact)).isEqualTo(contact);
    }



}
