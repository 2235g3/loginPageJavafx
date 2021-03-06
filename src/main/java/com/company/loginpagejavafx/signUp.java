package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class signUp {
    public static void addUserDetails(FileWriter myWriter, TextField passwordShownInput, PasswordField passwordInput, TextField usernameInput, Label errorText) {
        try {
            if (passwordShownInput.getText().length() >= 8) {
                myWriter.write(usernameInput.getText() + "," + passwordInput.getText() + ",");
            } else if (passwordInput.getText().length() >= 8) {
                myWriter.write(usernameInput.getText() + "," + passwordShownInput.getText() + ",");
            }
            myWriter.close();
        } catch (IOException e) {
            errorText.setText("An error occurred!");
            errorText.setVisible(true);
        }
    }

    public static void signUpCheck(userLogins[] userLoginsArr, ActionEvent event, TextField usernameInput, Label errorText, PasswordField passwordInput, TextField passwordShownInput) {
        boolean signUpCorrect = false;
        for (int i = 0; i < userLoginsArr.length; i+=1) {
            if (userLoginsArr[i].getUsername().equals(usernameInput.getText())) {
                signUpCorrect = true;
                break;
            }
        }
        if (signUpCorrect) {
            errorTextConfig(-50.0, "A user with this username already exists", true, errorText);
        } else {
            if (usernameInput.getText().length() < 3) {
                errorTextConfig(5.0, "Username too short", true, errorText);
            } else {
                if (passwordInput.getText().length() < 8 && passwordShownInput.getText().length() < 8) {
                    errorTextConfig(5.0, "Password too short", true, errorText);
                } else {
                    mainController.writeToFile("\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\userLogins.csv", "users", new ArrayList<>(), true, passwordShownInput, passwordInput, usernameInput, errorText, new Label());
                    new mainController().backToChoose(event);
                }
            }
        }
    }

    public static void errorTextConfig(double xTranslate, String errorMsg, boolean visible, Label errorText) {
        errorText.setTranslateX(xTranslate);
        errorText.setText(errorMsg);
        errorText.setVisible(visible);
    }
}