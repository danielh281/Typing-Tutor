/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dh.typing.tutor;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Daniel Haddadeen
 */
public class VirtualKeyboard {
    private GridPane root;
    private Map<String, Button> keyBtns;
    
    private static String[][] keys = new String[][] {
        {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
        {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
        {"SHIFT", "Z", "X", "C", "V", "B", "N", "M"}
    };
    
    public VirtualKeyboard() {
        root = new GridPane();
        keyBtns = new HashMap<>();
        
        for (int i = 0; i < keys.length; i++) {
            String[] rowOfKeys = keys[i];
            
            GridPane rowGrid = new GridPane(1, rowOfKeys.length);
            root.add(rowGrid, 0, i);
            
            for (int j = 0; j < rowOfKeys.length; j++) {
                String key = rowOfKeys[j];
                
                Button keyBtn = new Button(key);
                keyBtn.setMinHeight(30);
                
                if (!key.equals("SHIFT")) {
                    // Makes all normal keys the same square size
                    keyBtn.setMinWidth(30);
                }
                
                rowGrid.add(keyBtn, j, 0);
                keyBtns.put(key, keyBtn);
            }
        }
        
        root.setHgap(5);
        root.setVgap(5);
    }
    
    /**
     * Animates a key press on the virtual keyboard
     */
    public void pressKey() {
        
    }
    
    public GridPane getRoot() {
        return root;
    }
    
    public Map<String, Button> getKeyBtns() {
        return keyBtns;
    }
}
