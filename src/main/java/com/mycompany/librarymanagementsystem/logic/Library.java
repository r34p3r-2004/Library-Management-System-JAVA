/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.logic;

import com.mycompany.librarymanagementsystem.db.BookDAO;
import com.mycompany.librarymanagementsystem.db.BorrowRecordDAO;
import com.mycompany.librarymanagementsystem.db.MemberDAO;
import com.mycompany.librarymanagementsystem.model.Book;
import com.mycompany.librarymanagementsystem.model.BorrowRecord;
import com.mycompany.librarymanagementsystem.model.Member;

import java.util.Date;
import java.util.List;

public class Library {
    private BookDAO bookDAO;
    private MemberDAO memberDAO;
    private BorrowRecordDAO borrowRecordDAO;

    public Library() {
        this.bookDAO = new BookDAO();
        this.memberDAO = new MemberDAO();
        this.borrowRecordDAO = new BorrowRecordDAO();
    }

    // ==========================================================
    // Book Management Logic
    // ==========================================================

    public void addBook(Book book) throws Exception {
        // Validation: Empty Book ID[cite: 2]
        if (book.getItemId() == null || book.getItemId().trim().isEmpty()) {
            throw new Exception("Book ID cannot be empty.");
        }
        // Validation: Duplicate Book ID[cite: 2]
        if (bookDAO.getBookById(book.getItemId()) != null) {
            throw new Exception("A book with this ID already exists.");
        }
        
        boolean success = bookDAO.addBook(book);
        if (!success) {
            throw new Exception("Failed to add the book to the database.");
        }
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book searchBook(String bookId) throws Exception {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new Exception("Please enter a Book ID to search.");
        }
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new Exception("Book not found.");
        }
        return book;
    }

    public void removeBook(String bookId) throws Exception {
        Book book = searchBook(bookId);
        // Ensure we don't delete a book that is currently borrowed
        if (!book.isAvailable()) {
            throw new Exception("Cannot remove a book that is currently borrowed.");
        }
        boolean success = bookDAO.deleteBook(bookId);
        if (!success) {
            throw new Exception("Failed to remove the book.");
        }
    }

    // ==========================================================
    // Member Management Logic
    // ==========================================================

    public void addMember(Member member) throws Exception {
        if (member.getId() == null || member.getId().trim().isEmpty()) {
            throw new Exception("Member ID cannot be empty.");
        }
        if (memberDAO.getMemberById(member.getId()) != null) {
            throw new Exception("A member with this ID already exists.");
        }
        
        boolean success = memberDAO.addMember(member);
        if (!success) {
            throw new Exception("Failed to add the member to the database.");
        }
    }

    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public Member searchMember(String memberId) throws Exception {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new Exception("Please enter a Member ID to search.");
        }
        // Validation: Searching for a member who does not exist[cite: 2]
        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            throw new Exception("Member does not exist.");
        }
        return member;
    }

    // ==========================================================
    // Borrow & Return Logic
    // ==========================================================

    public void borrowBook(String bookId, String memberId) throws Exception {
        // 1. Validate inputs
        if (bookId == null || bookId.trim().isEmpty() || memberId == null || memberId.trim().isEmpty()) {
            throw new Exception("Book ID and Member ID are required.");
        }

        // 2. Check if member exists
        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            throw new Exception("Member does not exist.");
        }

        // 3. Check if book exists and is available
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new Exception("Book does not exist.");
        }
        
        // Validation: Attempting to borrow an already borrowed book[cite: 2]
        if (!book.isAvailable()) {
            throw new Exception("This book is already borrowed and not available.");
        }

        // 4. Create borrowing record[cite: 2]
        String recordId = "REC-" + System.currentTimeMillis(); // Generate a unique record ID
        BorrowRecord record = new BorrowRecord(recordId, bookId, memberId, new Date(), null, "Borrowed");
        
        boolean recordAdded = borrowRecordDAO.addBorrowRecord(record);
        if (!recordAdded) {
            throw new Exception("Failed to create borrow record.");
        }

        // 5. Update book status to "Borrowed" (is_available = false)[cite: 2]
        boolean statusUpdated = bookDAO.updateBookStatus(bookId, false);
        if (!statusUpdated) {
            throw new Exception("Failed to update book availability status.");
        }
    }

    public void returnBook(String bookId) throws Exception {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new Exception("Book ID is required.");
        }

        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new Exception("Book does not exist.");
        }

        // Validation: Attempting to return an available book[cite: 2]
        if (book.isAvailable()) {
            throw new Exception("This book is not currently borrowed.");
        }

        BorrowRecord activeRecord = borrowRecordDAO.getActiveRecordByBookId(bookId);
        if (activeRecord == null) {
            throw new Exception("No active borrow record found for this book.");
        }

        // Update the borrow record with the return date[cite: 2]
        boolean recordUpdated = borrowRecordDAO.markBookAsReturned(activeRecord.getRecordId(), new Date());
        if (!recordUpdated) {
            throw new Exception("Failed to update borrow record.");
        }

        // Update book status to "Available" (is_available = true)[cite: 2]
        boolean statusUpdated = bookDAO.updateBookStatus(bookId, true);
        if (!statusUpdated) {
            throw new Exception("Failed to update book availability status.");
        }
    }

    // ==========================================================
    // Dashboard Statistics Logic
    // ==========================================================
    
    public int getTotalBooksCount() {
        return bookDAO.getAllBooks().size();
    }
    
    public int getAvailableBooksCount() {
        return (int) bookDAO.getAllBooks().stream().filter(Book::isAvailable).count();
    }
    
    public int getBorrowedBooksCount() {
        return (int) bookDAO.getAllBooks().stream().filter(b -> !b.isAvailable()).count();
    }
    
    public int getTotalMembersCount() {
        return memberDAO.getAllMembers().size();
    }
}
