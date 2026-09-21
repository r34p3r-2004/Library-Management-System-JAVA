/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.db;

import com.mycompany.librarymanagementsystem.model.BorrowRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDAO {
    // 1. Create a new borrow record when a book is borrowed
    public boolean addBorrowRecord(BorrowRecord record) {
        String query = "INSERT INTO borrow_records (record_id, book_id, member_id, borrow_date, return_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, record.getRecordId());
            pstmt.setString(2, record.getBookId());
            pstmt.setString(3, record.getMemberId());
            pstmt.setDate(4, new java.sql.Date(record.getBorrowDate().getTime())); 
            
            if (record.getReturnDate() != null) {
                pstmt.setDate(5, new java.sql.Date(record.getReturnDate().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }
            
            pstmt.setString(6, record.getStatus()); // 'Borrowed' by default[cite: 3]
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Find an active borrow record for a specific book (needed for returning a book)
    public BorrowRecord getActiveRecordByBookId(String bookId) {
        String query = "SELECT * FROM borrow_records WHERE book_id = ? AND status = 'Borrowed'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, bookId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new BorrowRecord(
                        rs.getString("record_id"),
                        rs.getString("book_id"),
                        rs.getString("member_id"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date"),
                        rs.getString("status")
                    );
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if the book is not currently borrowed
    }

    // 3. Update the return date and status when a book is returned
    public boolean markBookAsReturned(String recordId, java.util.Date returnDate) {
        String query = "UPDATE borrow_records SET return_date = ?, status = 'Returned' WHERE record_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setDate(1, new java.sql.Date(returnDate.getTime()));
            pstmt.setString(2, recordId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Retrieve all borrow records (for history/dashboard purposes)
    public List<BorrowRecord> getAllBorrowRecords() {
        List<BorrowRecord> records = new ArrayList<>();
        String query = "SELECT * FROM borrow_records";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                BorrowRecord record = new BorrowRecord(
                    rs.getString("record_id"),
                    rs.getString("book_id"),
                    rs.getString("member_id"),
                    rs.getDate("borrow_date"),
                    rs.getDate("return_date"),
                    rs.getString("status")
                );
                records.add(record);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
