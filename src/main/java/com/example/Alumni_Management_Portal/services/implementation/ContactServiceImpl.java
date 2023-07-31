package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.entities.Contact;
import com.example.Alumni_Management_Portal.entities.ResourceNotFoundException;
import com.example.Alumni_Management_Portal.repositories.ContactRepository;
import com.example.Alumni_Management_Portal.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(int id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact existingContact = getContactById(contact.getId());
        existingContact.setUser(contact.getUser());
        existingContact.setPhoneNumber(contact.getPhoneNumber());
        existingContact.setStreet(contact.getStreet());
        existingContact.setCity(contact.getCity());
        existingContact.setState(contact.getState());
        existingContact.setZip(contact.getZip());
        return contactRepository.save(existingContact);
    }

    @Override
    public void deleteContact(int id) {
        Contact contact = getContactById(id);
        contactRepository.delete(contact);
    }
}
