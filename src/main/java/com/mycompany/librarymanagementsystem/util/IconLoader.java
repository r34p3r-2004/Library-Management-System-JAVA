/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem.util;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

public class IconLoader {
    /**
     * Loads an icon from the Resources.Icons package and scales it to the specified dimensions[cite: 1].
     * 
     * @param filename The name of the icon file (e.g., "3068327.png")
     * @param width    The desired width
     * @param height   The desired height
     * @return ImageIcon scaled to the requested size, or null if the file isn't found
     */
    public static ImageIcon loadIcon(String filename, int width, int height) {
        // Adjust the path based on where the Resources.Icons package is located in your classpath
        String path = "/Resources/Icons/" + filename;
        URL imgURL = IconLoader.class.getResource(path);
        
        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image img = originalIcon.getImage();
            Image resizedImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImg);
        } else {
            System.err.println("Error: Couldn't find icon file at " + path);
            return null;
        }
    }

    /**
     * Loads an icon without scaling it[cite: 1].
     * 
     * @param filename The name of the icon file
     * @return Original size ImageIcon, or null if the file isn't found
     */
    public static ImageIcon loadIcon(String filename) {
        String path = "/Resources/Icons/" + filename;
        URL imgURL = IconLoader.class.getResource(path);
        
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Error: Couldn't find icon file at " + path);
            return null;
        }
    }
}
