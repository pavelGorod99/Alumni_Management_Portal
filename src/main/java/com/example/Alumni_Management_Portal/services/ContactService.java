package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.entities.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();
    Contact getById(int id);
    Contact create(Contact contact);
    Contact update(Contact contact);
    void delete(int id);
}