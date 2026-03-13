package com.bilbioteca.pruebaTecnicaBiblioteca.Controllers;

import com.bilbioteca.pruebaTecnicaBiblioteca.Service.LoanService;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.Loan;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.User;
import com.bilbioteca.pruebaTecnicaBiblioteca.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;
    private final UserRepository userRepository;

    public LoanController(LoanService loanService,
                           UserRepository userRepository) {

        this.loanService = loanService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Loan createLoan(@RequestParam Long bookId,
                           Authentication auth){

        String username = auth.getName();

        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        return loanService.createLoan(user.getId(), bookId);
    }

    @GetMapping("/my")
    public List<Loan> myLoans(Authentication auth){

        String username = auth.getName();

        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        return loanService.getUserLoans(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Loan> getAllLoans(){
        return loanService.getAllLoans();
    }

    @PutMapping("/{id}/return")
    @PreAuthorize("hasRole('ADMIN')")
    public Loan returnBook(@PathVariable Long id){
        return loanService.returnBook(id);
    }

}
