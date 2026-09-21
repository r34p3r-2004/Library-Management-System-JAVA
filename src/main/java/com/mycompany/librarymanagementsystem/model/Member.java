/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.model;

/**
 *
 * @author sesan
 */

// Extends Person to demonstrate inheritance and object relationships[cite: 1, 2].
public class Member extends Person {

    // Constructor to initialize inherited attributes from the Person class[cite: 1, 2]
    public Member(String id, String name, String contactNumber) {
        super(id, name, contactNumber);
    }

    // Meaningful use of polymorphism by implementing the abstract method from Person[cite: 1, 2]
    @Override
    public String getRole() {
        return "Member";
    }
}