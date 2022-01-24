package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private static File userDetails = new File(System.getProperty("user.dir") + "\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\userLogins.csv");
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private TextField passwordShownInput;
    public boolean clicked = false;

    public void selectNewScene(String fileName, ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource(fileName));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
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
        selectNewScene("libraryInterface.fxml", event);
    }

    //Method to change a password field's visibility
    public void showHidePass(ActionEvent event) {
        clicked = new logIn().showHidePass(passwordInput, passwordShownInput, clicked);
    }

    //Method to check login details
    public void loginCheck(ActionEvent event) {
        new logIn().loginCheck(event, usernameInput, passwordInput, passwordShownInput, errorText);
    }

    //Method to read files
    public userLogins[] readFile() {
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
            return makeUserObjArr(userLogins);
        } catch (IOException e) {
            errorText.setText("An error occurred!");
            errorText.setVisible(true);
            return new userLogins[] {};
        }
    }

    public static userLogins[] makeUserObjArr(String[] userLoginsArr) {
        userLogins[] userLoginsObjArr = new userLogins[userLoginsArr.length / 2];
        int objArrIndex = 0;
        for (int i = 0; i < userLoginsArr.length; i+=2) {
            userLogins tempUserLogin = new userLogins(userLoginsArr[i], userLoginsArr[i + 1]);
            userLoginsObjArr[objArrIndex] = tempUserLogin;
            objArrIndex++;
        }
        return userLoginsObjArr;
    }

    // Sign up method
    public void signUp(ActionEvent event) {
        userLogins[] userLogins = readFile();
        signUpCheck(userLogins, event);
    }

    public void signUpCheck(userLogins[] userLogins, ActionEvent event) {
        signUp.signUpCheck(userLogins, event, usernameInput, errorText, passwordInput, passwordShownInput);
    }

    public static void writeToFile(String fileSource, String fileToEdit, ArrayList<book> bookDetails, boolean append, TextField passwordShownInput, PasswordField passwordInput, TextField usernameInput, Label errorText, Label errorLabel) {
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + fileSource, append);
            if (fileToEdit.equals("users")) {
                signUp.addUserDetails(myWriter, passwordShownInput, passwordInput, usernameInput, errorText);
            } else if (fileToEdit.equals("books")) {
                bookWrite(myWriter, bookDetails, errorLabel);
            }
        } catch (IOException e) {
            errorText.setText("An error occurred!");
            errorText.setVisible(true);
        }
    }

    public static void bookWrite(FileWriter myWriter, ArrayList<book> bookDetails, Label displayError) {
        try {
            for (book tempBookObj : bookDetails) {
                myWriter.write(tempBookObj.bookName + "," + tempBookObj.author + "," + tempBookObj.ISBN + "," + tempBookObj.genre);
            }
            myWriter.close();
        } catch (IOException e) {
            displayError.setText("An error occurred!");
            displayError.setVisible(true);
        }
    }
}