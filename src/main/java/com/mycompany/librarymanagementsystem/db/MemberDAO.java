/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.db;

import com.mycompany.librarymanagementsystem.model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    // 1. Add a new member to the database
    public boolean addMember(Member member) {
        String query = "INSERT INTO members (member_id, member_name, contact_number) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            // Assuming member.getId() gets the ID inherited from the Person class
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getContactNumber());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. View all members
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Member member = new Member(
                    rs.getString("member_id"),
                    rs.getString("member_name"),
                    rs.getString("contact_number")
                );
                members.add(member);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    // 3. Search for a member by their ID
    public Member getMemberById(String memberId) {
        String query = "SELECT * FROM members WHERE member_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Member(
                        rs.getString("member_id"),
                        rs.getString("member_name"),
                        rs.getString("contact_number")
                    );
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if the member does not exist
    }

    // 4. Remove a member (Optional enhancement for complete management)
    public boolean deleteMember(String memberId) {
        String query = "DELETE FROM members WHERE member_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, memberId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
