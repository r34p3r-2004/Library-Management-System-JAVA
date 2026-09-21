/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.librarymanagementsystem.ui;

import com.mycompany.librarymanagementsystem.logic.Library;

import javax.swing.*;
import java.awt.*;

public class ReturnPanel extends javax.swing.JPanel {

    private Library library;

    // UI Components[cite: 2]
    private JTextField txtBookId;
    private JButton btnReturn;

    public ReturnPanel(Library library) {
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
        formPanel.setBorder(BorderFactory.createTitledBorder("Return a Book"));
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

        // Return Button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnReturn = new JButton("Return Book");
        
        // Button styling
        btnReturn.setPreferredSize(new Dimension(150, 35));
        formPanel.add(btnReturn, gbc);

        add(formPanel, BorderLayout.CENTER);

        // --- Event Handling[cite: 2] ---
        
        // Delegate application logic to the Library class[cite: 2].
        btnReturn.addActionListener(e -> {
            String bookId = txtBookId.getText().trim();

            try {
                // Calls library.returnBook() to handle the business rules[cite: 1]
                library.returnBook(bookId);
                
                JOptionPane.showMessageDialog(this, 
                    "Book successfully returned!\nBook ID: " + bookId, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
                txtBookId.setText("");
                txtBookId.requestFocus();
                
            } catch (Exception ex) {
                // Validate user inputs and display appropriate messages (e.g., attempting to return an available book)[cite: 2].
                JOptionPane.showMessageDialog(this, 
                    ex.getMessage(), 
                    "Return Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
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
