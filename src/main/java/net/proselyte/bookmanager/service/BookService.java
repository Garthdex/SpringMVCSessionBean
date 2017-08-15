package net.proselyte.bookmanager.service;

import net.proselyte.bookmanager.model.Book;

import java.util.List;

public interface BookService {
    public void addBook(Book book);

    public Book updateBook(Book book);

    public Book removeBook(int id);

    public Book getBookById(int id);

    public List<Book> listBooks();
}
