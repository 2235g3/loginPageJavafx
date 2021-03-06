package com.company.loginpagejavafx;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class bookAdd {
    public static void addBook(TextField bookNameIn, TextField authorIn, TextField ISBNIn, TextField genreIn, Label errorLabel) {
        book[] readableBookDetails = libraryController.readFile();
        ArrayList<book> books = new ArrayList<>();
        for (book tempBookObj : readableBookDetails) {
            books.add(tempBookObj);
        }
        if (bookNameIn.getText().equals("") || authorIn.getText().equals("") || genreIn.getText().equals("")) {
            displayError(errorLabel, "One or more values are empty", 14.0, true);
        }
        else {
            boolean ISBNCorrect = checkDigit(ISBNIn.getText());
            if (!ISBNCorrect) {
                displayError(errorLabel, "ISBN is incorrect!", 53.0, true);
            } else {
                boolean bookExists = checkExisting(bookNameIn, authorIn, ISBNIn, readableBookDetails);
                if (bookExists) {
                    displayError(errorLabel, "Book is already in system!", 25.0, true);
                } else {
                    book newBook = new book(bookNameIn.getText(), authorIn.getText(), ISBNIn.getText(), genreIn.getText());
                    books.add(newBook);
                    mainController.writeToFile("\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\bookDetails.csv", "books", books, false, new TextField(), new PasswordField(), new TextField(), new Label(), errorLabel);
                    displayError(errorLabel, "Book was added to system!",0.0, true);
                    bookNameIn.setText("");
                    authorIn.setText("");
                    ISBNIn.setText("");
                    genreIn.setText("");
                }
            }
        }
    }

    public static boolean checkExisting(TextField bookNameIn, TextField authorIn, TextField ISBNIn, book[] readableBookDetails) {
        boolean bookExists = false;
        for (int i = 0; i < readableBookDetails.length; i++) {
            if ((bookNameIn.getText().equalsIgnoreCase(readableBookDetails[i].bookName) && authorIn.getText().equalsIgnoreCase(readableBookDetails[i].author)) || ISBNIn.getText().equals(readableBookDetails[i].ISBN)) {
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

    public static void displayError(Label errorLabel, String msgToDisplay, double xTranslate, boolean visible) {
        errorLabel.setText(msgToDisplay);
        errorLabel.setTranslateX(xTranslate);
        errorLabel.setVisible(visible);
    }
}