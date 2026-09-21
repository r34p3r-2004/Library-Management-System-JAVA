/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.model;

/**
 *
 * @author sesan
 */

// Abstract parent class defining shared properties for library items[cite: 1].
// Demonstrates abstraction and encapsulation, key OOP concepts required for the assessment[cite: 2].
public abstract class LibraryItem {

    // Private attributes to enforce encapsulation[cite: 2]
    private String itemId;
    private String title;
    private boolean isAvailable;

    // Constructor to initialize shared fields[cite: 1, 2]
    public LibraryItem(String itemId, String title, boolean isAvailable) {
        this.itemId = itemId;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    // Getters and setters for controlled data access[cite: 2]
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // Abstract method to be implemented by child classes[cite: 1]
    public abstract String getItemType();
}
