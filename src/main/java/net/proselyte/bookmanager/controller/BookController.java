package net.proselyte.bookmanager.controller;

import net.proselyte.bookmanager.model.Book;
import net.proselyte.bookmanager.service.BookService;
import net.proselyte.bookmanager.service.holders.BookHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    private BookHolder bookHolder;

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String bookList(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookHolder.getBooks());

        return "books";
    }

    @RequestMapping(value = "books/ajax", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> bookListAjax(){

        return bookHolder.getBooks();
    }

    @RequestMapping(value = "books/ajax/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Book getBookAjax(@PathVariable("id") int id){

        return bookHolder.getBookById(id);
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book){
        if(book.getId() == 0){
            book.setId(bookHolder.counter());
            bookHolder.addBook(book);
        }else {
            bookHolder.updateBook(book);
        }

        return "redirect:/books";
    }

    @RequestMapping(value = "/books/add/ajax", method = RequestMethod.POST)
    public @ResponseBody Book addBookAjax(@RequestBody Book book){
        bookHolder.addBook(book);

        return book;
    }

    @RequestMapping(value = "books/remove/{id}", method = RequestMethod.GET)
    public String removeBook(@PathVariable("id") int id){
        bookHolder.removeBook(id);

        return "redirect:/books";
    }

    @RequestMapping(value = "books/remove/ajax/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Book removeBookAjax(@PathVariable("id") int id){
        Book book = bookHolder.getBookById(id);
        bookHolder.removeBook(id);

        return book;
    }

    @RequestMapping(value = "books/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookHolder.getBookById(id));
        model.addAttribute("listBooks", bookHolder.getBooks());

        return "books";
    }

    @RequestMapping(value = "books/edit/ajax")
    @ResponseBody
    public Book editBook(@RequestBody Book book){
        Book bookObj = bookHolder.getBookById(book.getId());
        bookHolder.updateBook(book);

        return bookObj;
    }
}
