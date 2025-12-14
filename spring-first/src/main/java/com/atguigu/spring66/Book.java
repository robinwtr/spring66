package com.atguigu.spring66;

public class Book {
    private String bookName;
    private String author;
    private String publisher;
    public String getBookName() {
        return bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void toBook() {
        System.out.println("Book Name: " + bookName+" Author: " + author+" Publisher: " + publisher);
    }
    public static void main(String[] args) {
        Book book = new Book();
        book.setBookName("abc");
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
