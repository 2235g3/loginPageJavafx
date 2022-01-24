package com.company.loginpagejavafx;

public class book {
    protected String bookName;
    protected String author;
    protected String ISBN;
    protected String genre;

    public book(String bookName, String author, String ISBN, String genre) {
        this.bookName = bookName;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}