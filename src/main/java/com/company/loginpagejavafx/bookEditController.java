package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class bookEditController {
    @FXML
    private TextField bookNameInput;
    @FXML
    private TextField authorInput;
    @FXML
    private TextField ISBNInput;
    @FXML
    private TextField genreInput;
    @FXML
    private Label errorLabel;
    private String ogISBN;
    private String bookName;
    private String author;
    private String ISBN;
    private String genre;

    public void back(ActionEvent event) {
        new mainController().selectNewScene("libraryInterface.FXML", event);
    }

    public void signOut(ActionEvent event) {
        new mainController().backToChoose(event);
    }

    public void editBook(ActionEvent event) {
        new bookEdit().checkISBNBookName(libraryController.readFile(), bookNameInput, authorInput, ISBNInput, genreInput, errorLabel, new Button(), new Button(), new Label(), new Label(), new Label(), new Label(), new Label(), false, ogISBN, event);
    }

    public void setBaseValues(String bookNameInStr, String authorInStr, String ISBNInStr, String genreInStr) {
        bookName = bookNameInStr;
        author = authorInStr;
        ISBN = ISBNInStr;
        genre = genreInStr;
    }

    public void initialize() {
        bookNameInput.setText(bookName);
        authorInput.setText(author);
        ISBNInput.setText(ISBN);
        genreInput.setText(genre);
        ogISBN = ISBN;
    }
}