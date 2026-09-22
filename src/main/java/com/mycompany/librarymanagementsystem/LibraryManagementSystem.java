/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystem;

import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.librarymanagementsystem.logic.Library;
import com.mycompany.librarymanagementsystem.ui.MainFrame;
import javax.swing.SwingUtilities;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        // Run the GUI creation on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> {
            
            // 1. Initialize the core library logic (which will internally connect to DAOs/DB)
            Library library = new Library();
            
            // 2. Create the main UI frame, passing the library instance to be shared across panels
            MainFrame mainFrame = new MainFrame(library);
            
            // 3. Make the main application window visible
            mainFrame.setVisible(true);
            
        });
    }
}
