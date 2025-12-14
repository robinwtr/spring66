package com.atguigu.spring66.collectiontype;

import java.util.List;

public class Bookbook {
    private List<String> books;

    public void setBooks(List<String> books) {
        this.books = books;
    }

//    @Override
//    public String toString() {
//        return "Bookbook{" +
//                "books=" + books +
//                '}';
//    }
    public void print() {
        for (String book : books) {
            System.out.println(book);
        }
    }
}
