/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.librarymanagementsystem.ui;

import com.mycompany.librarymanagementsystem.logic.Library;

import javax.swing.*;
import java.awt.*;

public class BorrowPanel extends javax.swing.JPanel {

   private Library library;

    // UI Components[cite: 2]
    private JTextField txtBookId;
    private JTextField txtMemberId;
    private JButton btnBorrow;

    public BorrowPanel(Library library) {
        this.library = library;
        
        initComponents(); 
        
        removeAll();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        initCustomComponents(); 
    }

    private void initCustomComponents() {
        // --- Form Panel ---
        // Using GridBagLayout for a clean, centered form appearance
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Borrow a Book"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Book ID Input
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Book ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtBookId = new JTextField(15);
        formPanel.add(txtBookId, gbc);

        // Member ID Input
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Member ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        txtMemberId = new JTextField(15);
        formPanel.add(txtMemberId, gbc);

        // Borrow Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnBorrow = new JButton("Borrow Book");
        
        // Make the button stand out a bit
        btnBorrow.setPreferredSize(new Dimension(150, 35));
        formPanel.add(btnBorrow, gbc);

        add(formPanel, BorderLayout.CENTER);

        // --- Event Handling[cite: 2] ---
        
        // Do not implement the entire system only using button event code; application logic is delegated to the Library class[cite: 2].
        btnBorrow.addActionListener(e -> {
            String bookId = txtBookId.getText().trim();
            String memberId = txtMemberId.getText().trim();

            try {
                // Calls library.borrowBook() to handle the business rules[cite: 1]
                library.borrowBook(bookId, memberId);
                
                JOptionPane.showMessageDialog(this, 
                    "Book successfully borrowed!\nBook ID: " + bookId + "\nMember ID: " + memberId, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
                clearForm();
                
            } catch (Exception ex) {
                // The application should validate user inputs and display appropriate messages when an operation cannot be completed (e.g., Attempting to borrow an already borrowed book)[cite: 2].
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Borrowing Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void clearForm() {
        txtBookId.setText("");
        txtMemberId.setText("");
        txtBookId.requestFocus();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
