/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.librarymanagementsystem.ui;

import com.mycompany.librarymanagementsystem.logic.Library;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends javax.swing.JPanel {

    private Library library;

    // UI Components for the statistics
    private JLabel lblTotalBooks;
    private JLabel lblAvailableBooks;
    private JLabel lblBorrowedBooks;
    private JLabel lblTotalMembers;

    public DashboardPanel(Library library) {
        this.library = library;
        
        initComponents(); // 1. NetBeans runs its hidden code
        
        // 2. Clear NetBeans defaults and apply our layout
        removeAll();
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        initCustomComponents(); // 3. Add our UI elements
        refreshDashboard();
    }

    private void initCustomComponents() {
        // Title Label
        JLabel lblTitle = new JLabel("Library System Dashboard", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle, BorderLayout.NORTH);

        // Center Panel for Statistics Cards
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 20, 20));

        // Create individual cards for each statistic
        lblTotalBooks = createStatCard(statsPanel, "Total Books");
        lblAvailableBooks = createStatCard(statsPanel, "Available Books");
        lblBorrowedBooks = createStatCard(statsPanel, "Borrowed Books");
        lblTotalMembers = createStatCard(statsPanel, "Total Members");

        add(statsPanel, BorderLayout.CENTER);

        // Refresh Button at the bottom
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnRefresh = new JButton("Refresh Statistics");
        btnRefresh.setPreferredSize(new Dimension(150, 35));
        btnRefresh.addActionListener(e -> refreshDashboard());
        bottomPanel.add(btnRefresh);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Helper method to create visually distinct "cards" for the dashboard
    private JLabel createStatCard(JPanel parentPanel, String title) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel("0", SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valueLabel.setForeground(new Color(41, 128, 185)); // A nice shade of blue
        card.add(valueLabel, BorderLayout.CENTER);

        parentPanel.add(card);
        return valueLabel;
    }

    // Updates the dashboard numbers by querying the Library logic class[cite: 1]
    public void refreshDashboard() {
        lblTotalBooks.setText(String.valueOf(library.getTotalBooksCount()));
        lblAvailableBooks.setText(String.valueOf(library.getAvailableBooksCount()));
        lblBorrowedBooks.setText(String.valueOf(library.getBorrowedBooksCount()));
        lblTotalMembers.setText(String.valueOf(library.getTotalMembersCount()));
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
