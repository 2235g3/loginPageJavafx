package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class bookEdit {
    public void checkISBNBookName(book[] readableBookDetails, TextField bookNameToEdit, TextField ISBNToEdit, TextField authorToEdit, TextField genreToEdit, Label editErrorLabel, Button bookEditNo, Button bookEditYes, Label genreLabel, Label ISBNLabel, Label authorLabel, Label bookNameLabel, Label bookEditCheckLabel, boolean check, String ogISBN, ActionEvent event) {
        boolean bookExists = false;
        int bookIndex = -1;
        if (bookNameToEdit.getText().equals("") || ISBNToEdit.getText().equals("")) {
            bookAdd.displayError(editErrorLabel, "One or more values are empty", 28.0, true);
        }
        else {
            if (!bookAdd.checkDigit(ISBNToEdit.getText())) {
                bookAdd.displayError(editErrorLabel, "ISBN is incorrect!", 53.0, true);
            }
            else {
                for (int i = 0; i < readableBookDetails.length; i++) {
                    if (bookNameToEdit.getText().equalsIgnoreCase(readableBookDetails[i].bookName) && ISBNToEdit.getText().equals(readableBookDetails[i].ISBN)) {
                        bookIndex = i;
                        bookExists = true;
                        break;
                    }
                }
                if (!bookExists) {
                    bookAdd.displayError(editErrorLabel, "The ISBN and title you entered do not match up!", 0.0, true);
                }
                else {
                    bookAdd.displayError(editErrorLabel, "One or more values are empty", 28.0, false);
                    if (check) {
                        Node[] invisibleNodes = {bookEditNo, bookEditYes, genreLabel, ISBNLabel, authorLabel, bookNameLabel, bookEditCheckLabel};
                        setEverythingVisible(invisibleNodes, true);
                        displayBookDetails(readableBookDetails, bookIndex, genreLabel, ISBNLabel, authorLabel, bookNameLabel);
                    }
                    else {
                        readableBookDetails = editBookArr(readableBookDetails, ogISBN, bookNameToEdit.getText(), authorToEdit.getText(), ISBNToEdit.getText(), genreToEdit.getText());
                        ArrayList<book> books = new ArrayList<>();
                        for (book tempBookObj : readableBookDetails) {
                            books.add(tempBookObj);
                        }
                        mainController.writeToFile("\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\bookDetails.csv", "books", books, false, new TextField(), new PasswordField(), new TextField(), new Label(), new Label());
                        new mainController().selectNewScene("libraryInterface.FXML", event);
                    }
                }
            }
        }
    }

    public book[] editBookArr(book[] books, String ogISBN, String bookNameIn, String authorIn, String ISBNIn, String genreIn) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].ISBN.equals(ogISBN)) {
                books[i].bookName = bookNameIn;
                books[i].ISBN = ISBNIn;
                books[i].author = authorIn;
                books[i].genre = genreIn;
            }
        }
        return books;
    }

    public void setEverythingVisible(Node[] invisibleNodes, boolean intendedVisibility) {
        for (Node invisbleNode : invisibleNodes) {
            invisbleNode.setVisible(intendedVisibility);
        }
    }

    public void bookIncorrect(Button bookEditNo, Button bookEditYes, Label genreLabel, Label ISBNLabel, Label authorLabel, Label bookNameLabel, Label bookEditCheckLabel) {
        Node[] invisibleNodes = {bookEditNo, bookEditYes, genreLabel, ISBNLabel, authorLabel, bookNameLabel, bookEditCheckLabel};
        setEverythingVisible(invisibleNodes, false);
    }

    public void displayBookDetails(book[] readableBookDetails, int bookIndex, Label genreLabel, Label ISBNLabel, Label authorLabel, Label bookNameLabel) {
        bookNameLabel.setText(bookNameLabel.getText() + " " + readableBookDetails[bookIndex].bookName);
        authorLabel.setText(authorLabel.getText() + " " + readableBookDetails[bookIndex].author);
        ISBNLabel.setText(ISBNLabel.getText() + " " + readableBookDetails[bookIndex].ISBN);
        genreLabel.setText(genreLabel.getText() + " " + readableBookDetails[bookIndex].genre);
    }
}