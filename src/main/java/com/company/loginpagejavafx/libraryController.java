package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class libraryController {
    @FXML
    private TextField bookSearchBar;
    @FXML
    private TableView bookTable;
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
    @FXML
    private ChoiceBox filterChoice;

    private static ArrayList<String> bookDetails = new ArrayList<String>();
    private static File bookDetailsFile = new File(System.getProperty("user.dir") + "\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\bookDetails.csv");

    public void signOut(ActionEvent event) {
        new mainController().backToChoose(event);
    }

    public void bookSearch(ActionEvent event) {
        bookSearch.bookSearch(bookSearchBar, bookTable, filterChoice);
    }

    public void addABook(ActionEvent event) {
        bookAdd.addBook(bookNameIn, authorIn, ISBNIn, genreIn, errorLabel);
    }

    public static String[] readFile() {
        String[] readableDetails = new String[0];
        String data = "";
        Scanner fileReadInput;
        try {
            fileReadInput = new Scanner(bookDetailsFile);
            if (fileReadInput.hasNextLine()) {
                data = fileReadInput.nextLine();
            }
            bookDetails.add(data);
            for (String currBookDetail : bookDetails) {
                readableDetails = currBookDetail.split(",");
            }
            return readableDetails;
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred");
            return new String[]{""};
        }
    }

    public void initialize() {
        bookSearch.addFilterItems(filterChoice);
        setTableColumns();
        bookTable.setPlaceholder(new Label("No books currently in the system"));

        String[] readableBookDetails = readFile();
        if (readableBookDetails.length >= 4) {
            addBookItems(readableBookDetails);
        }
    }

    public void setTableColumns() {
        bookSearch.setTableColumns(bookTable);
    }

    public void addBookItems(String[] readableBookDetails) {
        bookSearch.addBookItems(readableBookDetails, bookTable);
    }
}