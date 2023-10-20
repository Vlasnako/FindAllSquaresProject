package com.example.coursework2;

import entity.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage1) throws IOException {
        stage = new Stage();
        stage.setTitle("Squares");
        stage.setMaximized(false);
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("start_window.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}