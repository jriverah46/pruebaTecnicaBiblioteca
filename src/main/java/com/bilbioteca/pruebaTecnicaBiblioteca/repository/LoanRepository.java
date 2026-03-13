package com.bilbioteca.pruebaTecnicaBiblioteca.repository;

import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.Loan;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.User;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByUser(User user);

    long countByUserAndStatus(User user, LoanStatus status);
}
