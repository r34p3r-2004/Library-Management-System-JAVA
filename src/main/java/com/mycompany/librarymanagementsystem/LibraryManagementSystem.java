/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystem;

import com.mycompany.librarymanagementsystem.db.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        try {

            try (Connection connection = DBConnection.getConnection()) {
                System.out.println("================================");
                System.out.println("DATABASE CONNECTION SUCCESSFUL");
                System.out.println("================================");
            }

        } catch (SQLException e) {

            System.out.println("================================");
            System.out.println("DATABASE CONNECTION FAILED");
            System.out.println("================================");

        }
    }
}
