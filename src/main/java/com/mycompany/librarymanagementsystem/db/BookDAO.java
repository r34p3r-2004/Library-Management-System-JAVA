/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.db;


import com.mycompany.librarymanagementsystem.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    // 1. Add a new book to the database
    public boolean addBook(Book book) {
        String query = "INSERT INTO books (book_id, title, author, category, is_available) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            // Assuming book.getItemId() gets the ID inherited from LibraryItem
            pstmt.setString(1, book.getItemId()); 
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getCategory());
            pstmt.setBoolean(5, book.isAvailable());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. View all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("book_id"),
                    rs.getString("title"),
                    rs.getBoolean("is_available"),
                    rs.getString("author"),
                    rs.getString("category")
                );
                books.add(book);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // 3. Search for a book by its ID
    public Book getBookById(String bookId) {
        String query = "SELECT * FROM books WHERE book_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, bookId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getBoolean("is_available"),
                        rs.getString("author"),
                        rs.getString("category")
                    );
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    // 4. Remove a book
    public boolean deleteBook(String bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, bookId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Update book availability status (used during Borrow/Return)
    public boolean updateBookStatus(String bookId, boolean isAvailable) {
        String query = "UPDATE books SET is_available = ? WHERE book_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setBoolean(1, isAvailable);
            pstmt.setString(2, bookId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
