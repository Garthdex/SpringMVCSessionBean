package net.proselyte.bookmanager.service;

import net.proselyte.bookmanager.dao.BookDao;
import net.proselyte.bookmanager.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        this.bookDao.updateBook(book);

        return book;
    }

    @Override
    @Transactional
    public Book removeBook(int id) {
        Book book = getBookById(id);
        this.bookDao.removeBook(id);

        return book;
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Override
    @Transactional
    public List<Book> listBooks() {
        return this.bookDao.listBooks();
    }
}
