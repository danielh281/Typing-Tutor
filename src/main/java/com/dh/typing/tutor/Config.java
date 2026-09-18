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
        {"", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]"},
        {"", "A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'"},
        {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", "."},
        {"Space"}
    };
    
    public static final Map<KeyCode, Integer> MIN_BUTTON_WIDTH = new HashMap<>(Map.of(
            KeyCode.SPACE, 200,
            KeyCode.SHIFT, 60,
            KeyCode.BACK_SPACE, 60
    ));
}
