package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class bookEditController {
    @FXML
    private TextField bookNameIn;
    @FXML
    private TextField authorIn;
    @FXML
    private TextField ISBNIn;
    @FXML
    private TextField genreIn;
    @FXML
    private Label errorLabel;
    private String ogISBN;

    public void back(ActionEvent event) {
        new mainController().selectNewScene("libraryInterface.FXML", event);
    }

    public void signOut(ActionEvent event) {
        new mainController().backToChoose(event);
    }

    public void editBook(ActionEvent event) {
        new bookEdit().checkISBNBookName(libraryController.readFile(), bookNameIn, authorIn, ISBNIn, genreIn, errorLabel, new Button(), new Button(), new Label(), new Label(), new Label(), new Label(), new Label(), false, ogISBN, event);
    }

    public void initialize(String bookName, String author, String ISBN, String genre) {
        bookNameIn.setText(bookName);
        authorIn.setText(author);
        ISBNIn.setText(ISBN);
        genreIn.setText(genre);
        ogISBN = ISBN;
    }
}