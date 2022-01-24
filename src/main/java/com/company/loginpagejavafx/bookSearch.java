package com.company.loginpagejavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class bookSearch {
    public static void bookSearch(TextField bookSearchBar, TableView bookTable, ChoiceBox filterChoice) {
        book[] readableBookDetails = libraryController.readFile();
        String searchIn = bookSearchBar.getText();
        if (searchIn.length() == 0) {
            bookTable.getItems().clear();
            addBookItems(readableBookDetails, bookTable);
        }
        else {
            int indexAdd = filterCheck(filterChoice);
            ArrayList<Integer> itemIndexes = indexCheck(readableBookDetails, bookSearchBar, indexAdd);
            displaySearchResults(itemIndexes, readableBookDetails, bookTable);
        }
    }

    public static int filterCheck(ChoiceBox filterChoice) {
        String[] choicesArr = {"Book Name", "Author", "ISBN", "Genre", "None"};
        for (int i = 0; i < choicesArr.length; i++) {
            if (filterChoice.getValue().equals(choicesArr[i])) {
                return i;
            }
        }
        return -2;
    }

    public static ArrayList<Integer> indexCheck(book[] readableBookDetails, TextField bookSearchBar, int indexAdd) {
        String searchIn = bookSearchBar.getText();
        ArrayList<Integer> itemIndexes = new ArrayList<>();
        for (int i = indexAdd; i < readableBookDetails.length; i++) {
            if (readableBookDetails[i].bookName.equalsIgnoreCase(searchIn) || readableBookDetails[i].author.equalsIgnoreCase(searchIn) || readableBookDetails[i].ISBN.equalsIgnoreCase(searchIn) || readableBookDetails[i].genre.equalsIgnoreCase(searchIn)) {
                itemIndexes.add(i);
            }
        }
        return itemIndexes;
    }

    public static void displaySearchResults(ArrayList<Integer> itemIndexes, book[] readableBookDetails, TableView bookTable) {
        ObservableList<Map<String, Object>> itemsToAdd = FXCollections.<Map<String, Object>>observableArrayList();
        for (int i : itemIndexes) {
            Map<String, Object> rowItems = new HashMap<>();
            rowItems.put("BN", readableBookDetails[i].bookName);
            rowItems.put("A", readableBookDetails[i].author);
            rowItems.put("I", readableBookDetails[i].ISBN);
            rowItems.put("G", readableBookDetails[i].genre);
            itemsToAdd.add(rowItems);
        }
        bookTable.getItems().setAll(itemsToAdd);
    }

    public static void setTableColumns(TableView bookTable) {
        TableColumn<Map, String> bookNameColumn = new TableColumn<>("Book Name");
        bookNameColumn.setCellValueFactory(new MapValueFactory<>("BN"));
        bookTable.getColumns().add(bookNameColumn);
        bookNameColumn.prefWidthProperty().bind(bookTable.widthProperty().multiply(0.3));

        TableColumn<Map, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new MapValueFactory<>("A"));
        bookTable.getColumns().add(authorColumn);
        authorColumn.prefWidthProperty().bind(bookTable.widthProperty().multiply(0.25));

        TableColumn<Map, String> ISBNColumn = new TableColumn<>("ISBN");
        ISBNColumn.setCellValueFactory(new MapValueFactory<>("I"));
        bookTable.getColumns().add(ISBNColumn);
        ISBNColumn.prefWidthProperty().bind(bookTable.widthProperty().multiply(0.25));

        TableColumn<Map, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new MapValueFactory<>("G"));
        bookTable.getColumns().add(genreColumn);
        genreColumn.prefWidthProperty().bind(bookTable.widthProperty().multiply(0.2));
    }

    public static void addBookItems(book[] readableBookDetails, TableView bookTable) {
        ObservableList<Map<String, Object>> itemsToAdd = FXCollections.<Map<String, Object>>observableArrayList();
        for (int i = 0; i < readableBookDetails.length; i++) {
            Map<String, Object> rowItems = new HashMap<>();
            rowItems.put("BN", readableBookDetails[i].bookName);
            rowItems.put("A", readableBookDetails[i].author);
            rowItems.put("I", readableBookDetails[i].ISBN);
            rowItems.put("G", readableBookDetails[i].genre);
            itemsToAdd.add(rowItems);
        }
        bookTable.getItems().addAll(itemsToAdd);
    }

    public static void addFilterItems(ChoiceBox filterChoice) {
        String[] choicesArr = {"None", "Book Name", "Author", "ISBN", "Genre"};
        for (String choice : choicesArr) {
            filterChoice.getItems().add(choice);
        }
        filterChoice.setValue("None");
    }
}