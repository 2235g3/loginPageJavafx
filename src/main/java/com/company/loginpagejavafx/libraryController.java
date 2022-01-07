package com.company.loginpagejavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.cell.MapValueFactory;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class libraryController {
    @FXML
    private TextField bookSearchBar;
    @FXML
    private TableView bookTable;

    private static ArrayList<String> bookDetails = new ArrayList<String>();
    private static File bookDetailsFile = new File("X:\\My Documents\\Reigate College\\Year 1\\Computer Science\\javaFX\\loginPageJavafx\\src\\main\\csvFiles\\com.comapny.loginpagejavafx\\bookDetails.csv");

    public void signOut(ActionEvent event) {
        new mainController().backToChoose(event);
    }

    public void bookSearch(ActionEvent event) {
        String[] readableBookDetails = readFile();

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
        setTableColumns();
        bookTable.setPlaceholder(new Label("No books currently in the system"));

        String[] readableBookDetails = readFile();
        if (readableBookDetails.length >= 4) {
            addBookItems(readableBookDetails);
        }
    }

    public void setTableColumns() {
        TableColumn<Map, String> bookNameColumn = new TableColumn<>("Book Name");
        bookNameColumn.setCellValueFactory(new MapValueFactory<>("Book Name"));
        bookTable.getColumns().add(bookNameColumn);
        bookNameColumn.prefWidthProperty().bind(bookTable.widthProperty().multiply(0.3));

        TableColumn<Map, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new MapValueFactory<>("Author"));
        bookTable.getColumns().add(authorColumn);
        authorColumn.prefWidthProperty().bind(bookTable.widthProperty().multiply(0.25));

        TableColumn<Map, String> ISBNColumn = new TableColumn<>("ISBN");
        ISBNColumn.setCellValueFactory(new MapValueFactory<>("ISBN"));
        bookTable.getColumns().add(ISBNColumn);
        ISBNColumn.prefWidthProperty().bind(bookTable.widthProperty().multiply(0.25));

        TableColumn<Map, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new MapValueFactory<>("Genre"));
        bookTable.getColumns().add(genreColumn);
        genreColumn.prefWidthProperty().bind(bookTable.widthProperty().multiply(0.2));
    }

    public void addBookItems(String[] readableBookDetails) {
        ObservableList<Map<String, Object>> itemsToAdd = FXCollections.<Map<String, Object>>observableArrayList();
        Map<String, Object> rowItems = new HashMap<>();
        for (int i = 0; i < readableBookDetails.length; i++) {
            rowItems.put("Book Name", readableBookDetails[i]);
            rowItems.put("Author", readableBookDetails[i + 1]);
            rowItems.put("ISBN", readableBookDetails[i + 2]);
            rowItems.put("Genre", readableBookDetails[i + 3]);
            itemsToAdd.addAll(rowItems);
            i += 3;
            rowItems.clear();
        }
        bookTable.getItems().addAll(itemsToAdd);
        System.out.println(bookTable.getItems());
    }
}