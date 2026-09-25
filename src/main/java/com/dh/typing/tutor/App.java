package com.dh.typing.tutor;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;


/**
 * GitHub Repository Link: https://github.com/danielh281/Typing-Tutor
 * @author Daniel Haddadeen
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        
        // Create the virtual keyboard & typing test and position them in the app
        VirtualKeyboard virtualKeyboard = new VirtualKeyboard();
        TypingTest typingTest = new TypingTest(virtualKeyboard);
        
        typingTest.getRoot().setAlignment(Pos.CENTER);
        root.setCenter(typingTest.getRoot());
        
        virtualKeyboard.getRoot().setAlignment(Pos.CENTER);
        root.setBottom(virtualKeyboard.getRoot());
        
        // Create the scene and listen for keyboard events
        Scene scene = new Scene(root, 600, 450);
        
        // Listen for keyboard inputs
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> typingTest.processKeyEvent(event));
        scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> typingTest.processKeyEvent(event));
        
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                event.consume();
            }
        });
        
        scene.getStylesheets().add("default.css");
        
        stage.setScene(scene);
        stage.setTitle("Typing Tutor");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}