package com.bilbioteca.pruebaTecnicaBiblioteca.repository;

import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}