package net.proselyte.bookmanager.controller;

import net.proselyte.bookmanager.model.Book;
import net.proselyte.bookmanager.service.BookService;
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

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String bookList(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "books";
    }

    @RequestMapping(value = "books/ajax", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> bookListAjax(){

        return bookService.listBooks();
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book){
        if(book.getId() == 0){
            this.bookService.addBook(book);
        }else {
            this.bookService.updateBook(book);
        }

        return "redirect:/books";
    }

    @RequestMapping(value = "/books/add/ajax", method = RequestMethod.POST)
    public @ResponseBody Book addBookAjax(@RequestBody Book book){
        this.bookService.addBook(book);

        return book;
    }

    @RequestMapping(value = "books/remove/{id}", method = RequestMethod.DELETE)
    public String removeBook(@PathVariable("id") int id){
        this.bookService.removeBook(id);

        return "redirect:/books";
    }

    @RequestMapping(value = "books/remove/ajax/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Book removeBookAjax(@PathVariable("id") int id){

        return this.bookService.removeBook(id);
    }

    @RequestMapping(value = "books/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "books";
    }

}
