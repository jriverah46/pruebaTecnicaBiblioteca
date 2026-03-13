package com.bilbioteca.pruebaTecnicaBiblioteca.Service;

import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.Book;
import com.bilbioteca.pruebaTecnicaBiblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }
    public Book updateBook(Long id, Book book){

        Book existing = getBook(id);

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setIsbn(book.getIsbn());
        existing.setTotal_copies(book.getTotal_copies());
        existing.setAvailable_copies(book.getAvailable_copies());

        return bookRepository.save(existing);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

}
