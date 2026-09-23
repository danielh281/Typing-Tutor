package com.dh.typing.tutor;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.input.KeyCode;

/**
 * 
 * @author Daniel Haddadeen
 */
public class Config {
    public static final String[] TYPING_SENTENCES = new String[] {
        "Try typing this test. Do it as quickly and accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog",
        "Five big quacking zephyrs jolt my wax bed",
        "Sympathizing would fix Quaker objectives",
        "A large fawn jumped quickly over white zinc boxes."
    };
    
    public static final String[][] HANDLED_KEYS = new String[][] {
        {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "Backspace"},
        {"", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
        {"", "A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'"},
        {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/"},
        {"Space"}
    };
    
    public static final Map<KeyCode, String> SPECIAL_SYMBOLS = new HashMap<>() {
        {
            KeyCode[] specialKeyCodes = new KeyCode[] {
                KeyCode.COMMA, KeyCode.PERIOD, KeyCode.SEMICOLON,
                KeyCode.QUOTE, KeyCode.MINUS, KeyCode.EQUALS,
                KeyCode.SLASH, KeyCode.DIGIT1, KeyCode.DIGIT2, 
                KeyCode.DIGIT3, KeyCode.DIGIT4, KeyCode.DIGIT5,
                KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8,
                KeyCode.DIGIT9, KeyCode.DIGIT0, KeyCode.SPACE,
            };

            String[] specialSymbols = new String[] {
                ",", ".", ";",
                "'", "-", "=",
                "/", "1", "2",
                "3", "4", "5",
                "6", "7", "8",
                "9", "0", " ",
            };

            for (int i = 0; i < specialKeyCodes.length; i++) {
                put(specialKeyCodes[i], specialSymbols[i]);
            }
        }
    };
    
    public static final Map<KeyCode, String> SHIFT_MODIFIED_SYMBOLS = new HashMap() {
        {
            KeyCode[] shiftModifiedKeyCodes = new KeyCode[] {
                KeyCode.COMMA, KeyCode.PERIOD, KeyCode.SEMICOLON,
                KeyCode.QUOTE, KeyCode.MINUS, KeyCode.EQUALS,
                KeyCode.SLASH, KeyCode.DIGIT1, KeyCode.DIGIT2, 
                KeyCode.DIGIT3, KeyCode.DIGIT4, KeyCode.DIGIT5,
                KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8,
                KeyCode.DIGIT9, KeyCode.DIGIT0
            };
            
            String[] shiftModifiedSymbols = new String[] {
                "<", ">", ":",
                "\"", "_", "+",
                "?", "!", "@",
                "#", "$", "%",
                "^", "&", "*",
                "(", ")"
            };
            
            for (int i = 0; i  < shiftModifiedKeyCodes.length; i++) {
                put(shiftModifiedKeyCodes[i], shiftModifiedSymbols[i]);
            }
        };
    };
    
    public static final Map<String, KeyCode> SYMBOL_TO_KEYCODE = new HashMap<>() {
        {
            String[] symbols = new String[] {
                ",", ".", ";",
                "'", "-", "=",
                "/", "1", "2",
                "3", "4", "5",
                "6", "7", "8",
                "9", "0", " "
            };

            KeyCode[] keyCodes = new KeyCode[] {
                KeyCode.COMMA, KeyCode.PERIOD, KeyCode.SEMICOLON,
                KeyCode.QUOTE, KeyCode.MINUS, KeyCode.EQUALS,
                KeyCode.SLASH, KeyCode.DIGIT1, KeyCode.DIGIT2,
                KeyCode.DIGIT3, KeyCode.DIGIT4, KeyCode.DIGIT5,
                KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8,
                KeyCode.DIGIT9, KeyCode.DIGIT0, KeyCode.SPACE
            };

            for (int i = 0; i < symbols.length; i++) {
                put(symbols[i], keyCodes[i]);
            }
        }  
    };
    
    public static final Map<KeyCode, Integer> MIN_BUTTON_WIDTH = new HashMap<>(Map.of(
            KeyCode.SPACE, 200,
            KeyCode.SHIFT, 60,
            KeyCode.BACK_SPACE, 60
    ));
}
