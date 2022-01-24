package com.company.loginpagejavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;


public class mainApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("chooseLogSign.FXML"));
            Scene chooseLogSignScene = new Scene(root);
            stage.setTitle("Account System");
            stage.setScene(chooseLogSignScene);
            stage.show();
        } catch(Exception e) {
            System.out.println("An Error Occurred!");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}