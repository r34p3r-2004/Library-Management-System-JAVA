/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.librarymanagementsystem.ui;

import com.mycompany.librarymanagementsystem.logic.Library;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    
    private Library library;
    private CardLayout cardLayout;
    private JPanel mainContentPanel;
    
    // UI Panels
    private DashboardPanel dashboardPanel;
    private BookManagementPanel bookManagementPanel;
    private MemberManagementPanel memberManagementPanel;
    private BorrowPanel borrowPanel;
    private ReturnPanel returnPanel;

    public MainFrame() {
        initComponents();
    }

    public MainFrame(Library library) {
        this.library = library;
        initComponents();       // 1. Let NetBeans run its locked code
        initCustomComponents(); // 2. Run our custom UI setup over it
    }

    private void initCustomComponents() {
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        setSize(900, 600);
        setLocationRelativeTo(null); 
        setTitle("Library Management System");

        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(6, 1, 10, 10)); 
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        sidebarPanel.setBackground(new Color(44, 62, 80));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton btnDashboard = createNavButton("Dashboard");
        JButton btnManageBooks = createNavButton("Manage Books");
        JButton btnManageMembers = createNavButton("Manage Members");
        JButton btnBorrowBook = createNavButton("Borrow Book");
        JButton btnReturnBook = createNavButton("Return Book");
        JButton btnExit = createNavButton("Exit");

        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnManageBooks);
        sidebarPanel.add(btnManageMembers);
        sidebarPanel.add(btnBorrowBook);
        sidebarPanel.add(btnReturnBook);
        sidebarPanel.add(btnExit);

        getContentPane().add(sidebarPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        dashboardPanel = new DashboardPanel(library);
        bookManagementPanel = new BookManagementPanel(library);
        memberManagementPanel = new MemberManagementPanel(library); 
        borrowPanel = new BorrowPanel(library);
        returnPanel = new ReturnPanel(library); 

        mainContentPanel.add(dashboardPanel, "Dashboard");
        mainContentPanel.add(bookManagementPanel, "ManageBooks");
        mainContentPanel.add(memberManagementPanel, "ManageMembers");
        mainContentPanel.add(borrowPanel, "BorrowBook");
        mainContentPanel.add(returnPanel, "ReturnBook");

        getContentPane().add(mainContentPanel, BorderLayout.CENTER);

        btnDashboard.addActionListener(e -> {
            dashboardPanel.refreshDashboard(); 
            cardLayout.show(mainContentPanel, "Dashboard");
        });
        btnManageBooks.addActionListener(e -> cardLayout.show(mainContentPanel, "ManageBooks"));
        btnManageMembers.addActionListener(e -> cardLayout.show(mainContentPanel, "ManageMembers"));
        btnBorrowBook.addActionListener(e -> cardLayout.show(mainContentPanel, "BorrowBook"));
        btnReturnBook.addActionListener(e -> cardLayout.show(mainContentPanel, "ReturnBook"));
        btnExit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        revalidate();
        repaint();
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(52, 73, 94));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(41, 128, 185));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(52, 73, 94));
            }
        });
        return button;
    }

    // ==========================================
    // YOUR LOCKED NETBEANS CODE STAYS RIGHT HERE
    // ==========================================
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  // ==========================================
    // PASTE THIS RIGHT AFTER THE LOCKED BLOCK
    // ==========================================
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            Library appLibrary = new Library(); 
            new MainFrame(appLibrary).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
