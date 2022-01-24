package com.company.loginpagejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

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
    @FXML
    private Label bookEditCheckLabel;
    @FXML
    private Label bookNameLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label ISBNLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private Button bookEditYes;
    @FXML
    private Button bookEditNo;
    @FXML
    private Label editErrorLabel;
    @FXML
    private TextField ISBNToEdit;
    @FXML
    private TextField bookNameToEdit;
    private Stage stage;
    private Scene newScene;
    private Parent root;

    private static ArrayList<String> bookDetails = new ArrayList<>();
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

    public void goToBookEdit(ActionEvent event) {
        selectEditScene("bookEdit.FXML", readFile(), event);
    }

    public void wrongBook(ActionEvent event) {
        new bookEdit().bookIncorrect(bookEditNo, bookEditYes, genreLabel, ISBNLabel, authorLabel, bookNameLabel, bookEditCheckLabel);
    }

    public static book[] readFile() {
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
            return makeBookObjArr(readableDetails);
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred");
            return new book[]{};
        }
    }

    public static book[] makeBookObjArr(String[] readableDetails) {
        book[] booksDetailsArr = new book[readableDetails.length / 4];
        for (int i = 0; i < readableDetails.length; i += 4) {
            book tempBookObj = new book(readableDetails[i], readableDetails[i + 1], readableDetails[i + 2], readableDetails[i + 3]);
            booksDetailsArr[i] = tempBookObj;
        }
        return booksDetailsArr;
    }

    public void initialize() {
        bookSearch.addFilterItems(filterChoice);
        setTableColumns();
        bookTable.setPlaceholder(new Label("No books currently in the system"));
        book[] bookDetailsObj = readFile();
        if (bookDetailsObj.length > 0) {
            addBookItems(bookDetailsObj);
        }
    }

    public void setTableColumns() {
        bookSearch.setTableColumns(bookTable);
    }

    public void addBookItems(book[] readableBookDetails) {
        bookSearch.addBookItems(readableBookDetails, bookTable);
    }

    public void bookToEditCheck(ActionEvent event) {
        new bookEdit().checkISBNBookName(readFile(), bookNameToEdit, ISBNToEdit, ISBNToEdit, ISBNToEdit, editErrorLabel, bookEditNo, bookEditYes, genreLabel, ISBNLabel, authorLabel, bookNameLabel, bookEditCheckLabel, true, "", event);
    }

    public void selectEditScene(String fileName, book[] books, ActionEvent event) {
        try {
            String author = "";
            String ISBN = "";
            String bookName = "";
            String genre = "";
            for (book tempBookObj : books) {
                if (tempBookObj.bookName.equalsIgnoreCase(bookNameToEdit.getText()) && tempBookObj.ISBN.equals(ISBNToEdit.getText())) {
                    bookName = tempBookObj.bookName;
                    author = tempBookObj.author;
                    ISBN = tempBookObj.ISBN;
                    genre = tempBookObj.genre;
                }
            }
            FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource(fileName));
            root = sceneLoader.load();
            bookEditController bookEditController = sceneLoader.getController();
            bookEditController.initialize(bookName, author, ISBN, genre);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        } catch (Exception e) {
            System.out.println("An Error Occurred!");
        }
    }
}