package com.example.simplegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TodoListApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TodoListApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),500,280);
        scene.getStylesheets().add("styles.css");
        stage.setTitle("Список дел");
        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(280);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}