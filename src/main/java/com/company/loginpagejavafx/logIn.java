package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class logIn {
    public int count;

    public void showHidePass(PasswordField passwordInput, TextField passwordShownInput) {
        count += 1;
        if (count % 2 == 1) {
            passVisibility(passwordInput.getText(), true, false, passwordInput, passwordShownInput);
        } else {
            passVisibility(passwordShownInput.getText(), false, true, passwordInput, passwordShownInput);
        }
    }

    public void passVisibility(String passVal, boolean passShow, boolean passHide, PasswordField passwordInput, TextField passwordShownInput) {
        passwordShownInput.setText(passVal);
        passwordShownInput.setVisible(passShow);
        passwordInput.setVisible(passHide);
    }

    //Method to check login details
    public void loginCheck(ActionEvent event, TextField usernameInput, PasswordField passwordInput, TextField passwordShownInput, Label errorText) {
        String[] userLogins = new mainController().readFile();
        boolean loginCorrect = false;
        if (userLogins.length > 0) {
            for (int i = 0; i < userLogins.length; i++) {
                if (userLogins[i].equals(usernameInput.getText()) && (userLogins[i + 1].equals(passwordInput.getText()) || userLogins[i + 1].equals(passwordShownInput.getText()))) {
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
