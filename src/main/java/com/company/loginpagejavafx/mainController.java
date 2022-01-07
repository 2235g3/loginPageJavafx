package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class mainController {

    //Scene switching methods
    private Stage stage;
    private Scene newScene;
    private Parent root;
    @FXML
    private Label errorText;
    private ArrayList<String> userDetailsArr = new ArrayList<>();
    private static File userDetails = new File("X:\\My Documents\\Reigate College\\Year 1\\Computer Science\\javaFX\\loginPageJavafx\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\userLogins.csv");
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private TextField passwordShownInput;
    public int count;

    public void selectNewScene(String fileName, ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource(fileName));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        } catch (Exception e) {
            System.out.println("An Error Occurred!");
        }
    }

    public void logInPage(ActionEvent event) {
        selectNewScene("logInWall.FXML", event);
    }

    public void signUpPage(ActionEvent event) {
        selectNewScene("signUpPage.FXML", event);
    }

    public void backToChoose(ActionEvent event) {
        selectNewScene("chooseLogSign.FXML", event);
    }

    public void successPage(ActionEvent event) {
        selectNewScene("successPage.FXML", event);
    }

    //Method to change a password field's visibility
    public void showHidePass(ActionEvent event) {
        String passwordText;
        count += 1;
        if (count % 2 == 1) {
            passwordText = passwordInput.getText();
            passwordShownInput.setText(passwordText);
            passwordShownInput.setVisible(true);
            passwordInput.setVisible(false);
        } else {
            passwordText = passwordShownInput.getText();
            passwordInput.setText(passwordText);
            passwordInput.setVisible(true);
            passwordShownInput.setVisible(false);
        }
    }

    //Method to check login details
    public void loginCheck(ActionEvent event) {
        String[] userLogins = readFile();
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
                successPage(event);
            }
            else {
                errorText.setText("Username and/or password incorrect!");
                errorText.setTranslateX(-46.0);
                errorText.setVisible(true);
            }
        }
    }

    //Method to read files
    public String[] readFile() {
        try {
            String[] userLogins = new String[0];
            Scanner fileInput = new Scanner(userDetails);
            String data = "";
            if (fileInput.hasNextLine()) {
                data = fileInput.nextLine();
            }
            userDetailsArr.clear();
            userDetailsArr.add(data);
            for (String currVal : userDetailsArr) {
                userLogins = currVal.split(",");
            }
            return userLogins;
        } catch (IOException e) {
            errorText.setText("An error occurred!");
            errorText.setVisible(true);
            return new String[]{""};
        }
    }

    // Sign up method
    public void signUp(ActionEvent event) {
        String[] userLogins = readFile();
        signUpCheck(userLogins, event);
    }

    public void signUpCheck(String[] userLogins, ActionEvent event) {
        boolean signUpCorrect = false;
        for (int i = 0; i < userLogins.length; i++) {
            if (userLogins[i].equals(usernameInput.getText())) {
                signUpCorrect = true;
                break;
            }
            i += 1;
        }
        if (signUpCorrect) {
            errorText.setText("A user with this username already exists");
            errorText.setTranslateX(-50.0);
            errorText.setVisible(true);
        } else {
            if (usernameInput.getText().length() < 3) {
                errorText.setTranslateX(5.0);
                errorText.setText("Username too short");
                errorText.setVisible(true);
            } else {
                if (passwordInput.getText().length() < 8 && passwordShownInput.getText().length() < 8) {
                    errorText.setTranslateX(5.0);
                    errorText.setText("Password too short");
                    errorText.setVisible(true);
                } else {
                    writeToFile();
                    backToChoose(event);
                }
            }
        }
    }

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter("X:\\My Documents\\Reigate College\\Year 1\\Computer Science\\javaFX\\loginPageJavafx\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\userLogins.csv", true);
            if (passwordShownInput.getText().length() < 8) {
                myWriter.write(usernameInput.getText() + "," + passwordInput.getText() + ",");
            }
            else if (passwordInput.getText().length() < 8) {
                myWriter.write(usernameInput.getText() + "," + passwordShownInput.getText() + ",");
            }
            myWriter.close();
        } catch (IOException e) {
            errorText.setText("An error occurred!");
            errorText.setVisible(true);
        }
    }
}