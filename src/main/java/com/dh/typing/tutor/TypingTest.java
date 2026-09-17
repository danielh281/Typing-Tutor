package com.dh.typing.tutor;

import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.Map;

/**
 *
 * @author Daniel Haddadeen
 */
public class TypingTest {
    private VBox root;
    private Label sentencePrompt;
    private Label invalidKeyAlert;
    private TextField sentenceField;
    private Map<String, Boolean> pressedKeys = new HashMap<>();
    private VirtualKeyboard virtualKeyboard;
    
    public TypingTest(VirtualKeyboard virtualKeyboard) {
        this.virtualKeyboard = virtualKeyboard;
        sentencePrompt = new Label("Test");
        invalidKeyAlert = new Label();
        sentenceField = new TextField();
        
        sentenceField.setFocusTraversable(false);
        
        sentenceField.setEditable(false);
        sentenceField.setMaxWidth(450);
        
        root = new VBox(10, sentencePrompt, sentenceField);
    }
    
    /**
     * Processes a key pressed JavaFX event.
     * @param event The key pressed event
     */
    public void processKeyEvent(KeyEvent event) {
        String keyName = event.getCode().toString();
        boolean isPressedEvent = event.getEventType() == KeyEvent.KEY_PRESSED;
        Boolean isPressed = pressedKeys.get(keyName);
        
        // Prevents the key event from being triggered multiple times
        if (isPressed != null && isPressedEvent == isPressed) {
            return;
        }
        
        pressedKeys.put(keyName, isPressedEvent);
    
        Button keyBtn = virtualKeyboard.getKeyButton(keyName);
        
        // Show the key alert if the key is not handled
        if (keyBtn == null) {
            displayInvalidKeyAlert(true);
            return;
        } else if (isPressedEvent) {
            // Hide the invalid key alert on the next key press
            displayInvalidKeyAlert(false);
        }
        
        // Key Animation Logic
        String style = isPressedEvent ? "" : "";
        keyBtn.setStyle(style); // TODO: add css styling
        
        System.out.println("Key " + (isPressedEvent ? "Pressed" : "Released"));
    }
    
    /**
     * Displays or hides an invalid key alert depending on the passed showing parameter.
     * @param showing Whether or not to show the alert
     */
    public void displayInvalidKeyAlert(boolean showing) {
        String text = showing ? "Not Handled" : "";
        invalidKeyAlert.setText(text);
    }
   
    public VBox getRoot() {
        return root;
    }
}
