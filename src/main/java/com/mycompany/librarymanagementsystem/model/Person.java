/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.model;

/**
 *
 * @author sesan
 */

// Abstract parent class defining shared properties for individuals in the system[cite: 1].
// Demonstrates abstraction and encapsulation[cite: 2].
public abstract class Person {

    // Private attributes to enforce encapsulation[cite: 2]
    private String id;
    private String name;
    private String contactNumber;

    // Constructor to initialize shared fields[cite: 1, 2]
    public Person(String id, String name, String contactNumber) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    // Getters and setters for controlled data access[cite: 2]
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // Abstract method to be implemented by child classes[cite: 1]
    public abstract String getRole();
}
