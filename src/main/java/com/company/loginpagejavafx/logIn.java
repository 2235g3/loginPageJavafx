package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class logIn {

    public boolean showHidePass(PasswordField passwordInput, TextField passwordShownInput, boolean clicked) {
        clicked = !clicked;
        if (clicked) {
            passVisibility(passwordInput.getText(), true, passwordShownInput, passwordInput);
        } else {
            passVisibility(passwordShownInput.getText(), false, passwordShownInput, passwordInput);
        }
        return clicked;
    }

    public void passVisibility(String textVal, boolean passShow, TextField passwordShownInput, PasswordField passwordInput) {
        if (passShow) {
            passwordShownInput.setText(textVal);
            passwordShownInput.setVisible(true);
            passwordInput.setVisible(false);
        }
        else {
            passwordInput.setText(textVal);
            passwordInput.setVisible(true);
            passwordShownInput.setVisible(false);
        }
    }

    //Method to check login details
    public void loginCheck(ActionEvent event, TextField usernameInput, PasswordField passwordInput, TextField passwordShownInput, Label errorText) {
        userLogins[] userLoginsArr = new mainController().readFile();
        boolean loginCorrect = false;
        if (userLoginsArr.length > 0) {
            for (int i = 0; i < userLoginsArr.length; i++) {
                if (userLoginsArr[i].getUsername().equals(usernameInput.getText()) && (userLoginsArr[i].getPassword().equals(passwordInput.getText()) || userLoginsArr[i].getPassword().equals(passwordShownInput.getText()))) {
                    loginCorrect = true;
                    break;
                }
                i += 1;
            }
            if (loginCorrect) {
                new mainController().successPage(event);
            } else {
                signUp.errorTextConfig(-46.0, "Username and/or password incorrect!", true, errorText);
            }
        }
    }
}