package com.company.loginpagejavafx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class bookEdit {
    public void checkISBNBookName(String[] readableBookDetails, TextField bookNameToEdit, TextField ISBNToEdit, Label editErrorLabel, Button bookEditNo, Button bookEditYes, Label genreLabel, Label ISBNLabel, Label authorLabel, Label bookNameLabel, Label bookEditCheckLabel) {
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
                for (int i = 0; i < readableBookDetails.length; i+=4) {
                    if (bookNameToEdit.getText().equalsIgnoreCase(readableBookDetails[i]) && ISBNToEdit.getText().equals(readableBookDetails[i + 2])) {
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
                    Node[] invisibleNodes = {bookEditNo, bookEditYes, genreLabel, ISBNLabel, authorLabel, bookNameLabel, bookEditCheckLabel};
                    setEverythingVisible(invisibleNodes, true);
                    displayBookDetails(readableBookDetails, bookIndex, genreLabel, ISBNLabel, authorLabel, bookNameLabel);
                }
            }
        }
    }

    public void setEverythingVisible(Node[] invisibleNodes, boolean intendedVisibility) {
        for (Node invisbleNode : invisibleNodes) {
            invisbleNode.setVisible(intendedVisibility);
        }
    }

    public void displayBookDetails(String[] readableBookDetails, int bookIndex, Label genreLabel, Label ISBNLabel, Label authorLabel, Label bookNameLabel) {
        bookNameLabel.setText(bookNameLabel.getText() + " " + readableBookDetails[bookIndex]);
        authorLabel.setText(authorLabel.getText() + " " + readableBookDetails[bookIndex + 1]);
        ISBNLabel.setText(ISBNLabel.getText() + " " + readableBookDetails[bookIndex + 2]);
        genreLabel.setText(genreLabel.getText() + " " + readableBookDetails[bookIndex + 3]);
    }
}
