package com.company.loginpagejavafx;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class bookAdd {
    public static void addBook(TextField bookNameIn, TextField authorIn, TextField ISBNIn, TextField genreIn, Label errorLabel) {
        ArrayList<String> newBookDetails = new ArrayList<>();
        String[] readableBookDetails = libraryController.readFile();
        if (bookNameIn.getText().equals("") || authorIn.getText().equals("") || genreIn.getText().equals("")) {
            displayError(errorLabel, "One or more values are empty", 14.0);
        }
        else {
            boolean ISBNCorrect = checkDigit(ISBNIn.getText());
            if (!ISBNCorrect) {
                displayError(errorLabel, "ISBN is incorrect!", 53.0);
            } else {
                boolean bookExists = checkExisting(bookNameIn, authorIn, ISBNIn, readableBookDetails);
                if (bookExists) {
                    displayError(errorLabel, "Book is already in system!", 25.0);
                } else {
                    for (String detail : readableBookDetails) {
                        newBookDetails.add(detail);
                    }
                    newBookDetails.add(bookNameIn.getText());
                    newBookDetails.add(authorIn.getText());
                    newBookDetails.add(ISBNIn.getText());
                    newBookDetails.add(genreIn.getText());
                    mainController.writeToFile("\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\bookDetails.csv", "books", newBookDetails, false, new TextField(), new PasswordField(), new TextField(), new Label(), errorLabel);
                    displayError(errorLabel, "Book was added to system!",0.0);
                    bookNameIn.setText("");
                    authorIn.setText("");
                    ISBNIn.setText("");
                    genreIn.setText("");
                }
            }
        }
    }

    public static boolean checkExisting(TextField bookNameIn, TextField authorIn, TextField ISBNIn, String[] readableBookDetails) {
        boolean bookExists = false;
        for (int i = 0; i < readableBookDetails.length; i += 4) {
            if ((bookNameIn.getText().equalsIgnoreCase(readableBookDetails[i]) && authorIn.getText().equalsIgnoreCase(readableBookDetails[i + 1])) || ISBNIn.getText().equals(readableBookDetails[i + 2])) {
                bookExists = true;
                break;
            }
        }
        return bookExists;
    }

    public static boolean checkDigit(String ISBN) {
        int total = findCheckDigitTotal(ISBN);
        if (total == -1) {
            return false;
        }
        if (total == 0) {
            total = 10;
        }
        if (ISBN.endsWith((Integer.toString(10 - total)))) {
            return true;
        }
        return false;
    }

    public static int findCheckDigitTotal(String ISBN) {
        try {
            int total = 0;
            for (int i = 0; i < (ISBN.length() - 1); i++) {
                if (i % 2 == 1) {
                    total += Integer.parseInt(String.valueOf(ISBN.charAt(i))) * 3;
                } else {
                    total += Integer.parseInt(String.valueOf(ISBN.charAt(i)));
                }
            }
            return (total % 10);
        } catch (Exception e) {
            return -1;
        }
    }

    public static void displayError(Label errorLabel, String msgToDisplay, double xTranslate) {
        errorLabel.setText(msgToDisplay);
        errorLabel.setTranslateX(xTranslate);
        errorLabel.setVisible(true);
    }
}