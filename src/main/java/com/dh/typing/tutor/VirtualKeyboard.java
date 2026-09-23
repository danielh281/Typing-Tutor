package com.dh.typing.tutor;

import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Daniel Haddadeen
 */
public class VirtualKeyboard {
    private GridPane root;
    private Map<KeyCode, Button> keyBtns;

    public VirtualKeyboard() {
        root = new GridPane();
        keyBtns = new HashMap<>();
        
        for (int i = 0; i < Config.HANDLED_KEYS.length; i++) {
            String[] rowOfKeys = Config.HANDLED_KEYS[i];
            
            GridPane rowGrid = new GridPane(1, rowOfKeys.length);
            rowGrid.setAlignment(Pos.CENTER);
            rowGrid.setHgap(3);
            root.add(rowGrid, 0, i);
            
            for (int j = 0; j < rowOfKeys.length; j++) {
                String key = rowOfKeys[j];
                
                if (key.isEmpty()) {
                    continue;
                }
                
                Button keyBtn = new Button(key);
                keyBtn.setMinHeight(30);
                keyBtn.setFocusTraversable(false);
                keyBtn.getStylesheets().add("releasedKey.css");
                
                int minWidth = 30;
                
                if (Config.MIN_BUTTON_WIDTH.get(KeyCode.getKeyCode(key)) != null) {
                    minWidth = Config.MIN_BUTTON_WIDTH.get(KeyCode.getKeyCode(key));
                }
                
                keyBtn.setMinWidth(minWidth);
                
                rowGrid.add(keyBtn, j, 0);
                
                KeyCode keyCode = (KeyCode.getKeyCode(key) != null) ?
                        KeyCode.getKeyCode(key) : Config.SYMBOL_TO_KEYCODE.get(key);
                
                System.out.println(keyCode);
                keyBtns.put(keyCode, keyBtn);
            }
        }
        
        root.setHgap(5);
        root.setVgap(5);
    }
    
    /**
     * Applies visuals for the key press on the corresponding key button
     * @param keyCode The KeyCode of the button to apply the visuals to
     */
    public void pressKey(KeyCode keyCode) {
        Button keyBtn = getKeyButton(keyCode);
        keyBtn.getStylesheets().add("pressedKey.css");
    }
    
    /**
     * Removes pressed visuals for the corresponding key button
     * @param keyCode The KeyCode of the button to remove the visuals from
     */
    public void releaseKey(KeyCode keyCode) {
        Button keyBtn = getKeyButton(keyCode);
        keyBtn.getStylesheets().remove("pressedKey.css");
    }
    
    /**
     * Fetches and returns the key button on the virtual keyboard according to a KeyCode.
     * @param keyCode The KeyCode of the key.
     * @return The corresponding key button, if it exists.
     */
    public Button getKeyButton(KeyCode keyCode) {
        return keyBtns.get(keyCode);
    }
    
    public GridPane getRoot() {
        return root;
    }
}
