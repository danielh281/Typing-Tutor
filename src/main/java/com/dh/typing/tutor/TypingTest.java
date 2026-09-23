package com.dh.typing.tutor;

import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.util.Map;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Daniel Haddadeen
 */
public class TypingTest {
    private VBox root;
    private Label sentencePrompt;
    private Label invalidKeyAlert;
    private Label accuracyMeter;
    private TextField sentenceField;
    private Button nextButton;
    private Button resetButton;
    private VirtualKeyboard virtualKeyboard;
    private Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
    private String currentPrompt = Config.TYPING_SENTENCES[0];
    private int currentPromptIndex = 0;
    
    public TypingTest(VirtualKeyboard virtualKeyboard) {
        this.virtualKeyboard = virtualKeyboard;
        sentencePrompt = new Label("Test");
        invalidKeyAlert = new Label();
        accuracyMeter = new Label();
        sentenceField = new TextField();
        nextButton = new Button("Next");
        resetButton = new Button("Reset");
        sentenceField.setFocusTraversable(false);
        
        sentenceField.setEditable(false);
        sentenceField.setMaxWidth(450);
        
        // Listen for next button activation
        nextButton.setOnAction(event -> {
            // We use modulo by the amount of typing sentences so it can cycle
            currentPromptIndex = (currentPromptIndex + 1) % Config.TYPING_SENTENCES.length;
            currentPrompt = Config.TYPING_SENTENCES[currentPromptIndex];
            displayPrompt(currentPromptIndex);
            displayKeyAlert("");
            sentenceField.setText("");
            updateAccuracy();
        });
        
        nextButton.setFocusTraversable(false);
        
        // Listen for reset button activation
        resetButton.setOnAction(event -> {
            currentPromptIndex = 0;
            currentPrompt = Config.TYPING_SENTENCES[currentPromptIndex];
            displayPrompt(currentPromptIndex);
            displayKeyAlert("");
            sentenceField.setText("");
            updateAccuracy();
        });
        
        resetButton.setFocusTraversable(false);
        
        // Show initial prompt
        displayPrompt(0);
        
        root = new VBox(10,
                invalidKeyAlert,
                sentencePrompt,
                accuracyMeter,
                sentenceField,
                nextButton,
                resetButton
        );
        
        updateAccuracy();
    }
    
    /**
     * Processes a key pressed JavaFX event.
     * @param event The key pressed event
     */
    public void processKeyEvent(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        boolean isPressedEvent = event.getEventType() == KeyEvent.KEY_PRESSED;
        Boolean isPressed = pressedKeys.get(keyCode);
        
        // Prevents the key event from being triggered multiple times
        if (isPressed != null && isPressedEvent == isPressed) {
            return;
        }
        
        pressedKeys.put(keyCode, isPressedEvent);
    
        Button keyBtn = virtualKeyboard.getKeyButton(keyCode);
        
        // Show the key alert
        if (keyBtn == null) {
            displayKeyAlert("Not handled.");
            return;
        } else if (isPressedEvent) {
            displayKeyAlert(keyCode.toString());
        }
        
        // Key animatoin logic
        String style = isPressedEvent ? "" : "";
        keyBtn.setStyle(style); // TODO: add css styling
        
        // Text appending logic
        String currentText = sentenceField.getText();
        
        if (!isPressedEvent) {
            return;
        } else if (keyCode.equals(KeyCode.BACK_SPACE)) {
            // Delete the previous character
            int endIdx = Math.max(currentText.length() - 1, 0);
            sentenceField.setText(currentText.substring(0, endIdx));
        } else if (!keyCode.isLetterKey()){
            // Use the corresponding symbol depending if shift is pressed
            String symbol = (event.isShiftDown()) ? 
                    Config.SHIFT_MODIFIED_SYMBOLS.get(keyCode)
                    : Config.SPECIAL_SYMBOLS.get(keyCode);
            
            if (symbol != null) {
                sentenceField.setText(currentText + symbol);
            }
        } else {
            // Create the letter in either uppercase or lowercase
            String letter = (event.isShiftDown()) ? keyCode.toString() : keyCode.toString().toLowerCase();
            sentenceField.setText(currentText + letter);
        }
        
        updateAccuracy();
    }
    
    /**
     * Displays or hides an invalid key alert depending on the passed showing parameter.
     * @param showing Whether or not to show the alert
     */
    public void displayKeyAlert(String alert) {
        invalidKeyAlert.setText(alert);
    }
    
    public void displayPrompt(int promptIndex) {
        sentencePrompt.setText(Config.TYPING_SENTENCES[promptIndex]);
    }
    
    public void updateAccuracy() {
        char[] promptChars = currentPrompt.toCharArray();
        char[] inputChars = sentenceField.getText().toCharArray();
        
        double totalChars = promptChars.length;
        double correctChars = 0;
        
        for (int i = 0; i < promptChars.length; i++) {
            Character correctChar = promptChars[i];
            Character inputChar = null;
            
            if (i >= 0 && inputChars.length > 0 && i < inputChars.length) {
                inputChar = inputChars[i];
                System.out.println("Found character: " + inputChar);
            }
            
            if (inputChar != null && inputChar.equals(correctChar)) {
                correctChars++;
                System.out.println("Correct character");
            }
        }
        
        double correctPercentange = (correctChars / totalChars) * 100;
        
        accuracyMeter.setText(String.format("Accuracy: %.1f [%.0f/%.0f]", correctPercentange, correctChars, totalChars));
    }
   
    public VBox getRoot() {
        return root;
    }
}
