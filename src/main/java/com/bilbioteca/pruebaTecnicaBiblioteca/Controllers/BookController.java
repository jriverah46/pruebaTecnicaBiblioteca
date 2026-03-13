package com.bilbioteca.pruebaTecnicaBiblioteca.Controllers;

import com.bilbioteca.pruebaTecnicaBiblioteca.Service.BookService;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.Book;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Book updateBook(@PathVariable Long id,
                           @RequestBody Book book){

        return bookService.updateBook(id, book);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}


