package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.entities.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts();
    Contact getContactById(int id);
    Contact createContact(Contact contact);
    Contact updateContact(Contact contact);
    void deleteContact(int id);
}