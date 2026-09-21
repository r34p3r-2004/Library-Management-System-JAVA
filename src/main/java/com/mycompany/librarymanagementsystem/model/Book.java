/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.model;

/**
 *
 * @author sesan
 */
// Extends LibraryItem to demonstrate inheritance and object relationships[cite: 2].
public class Book extends LibraryItem {

    // Private attributes to enforce encapsulation[cite: 2]
    private String author;
    private String category;

    // Constructor to initialize inherited attributes and Book-specific attributes[cite: 2]
    public Book(String itemId, String title, boolean isAvailable, String author, String category) {
        super(itemId, title, isAvailable);
        this.author = author;
        this.category = category;
    }

    // Getters and setters for appropriate data access[cite: 2]
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Meaningful use of polymorphism by implementing the abstract method from LibraryItem[cite: 2]
    @Override
    public String getItemType() {
        return "Book";
    }
}
