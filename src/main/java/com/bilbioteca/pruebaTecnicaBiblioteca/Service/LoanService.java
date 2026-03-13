package com.bilbioteca.pruebaTecnicaBiblioteca.Service;

import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.Book;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.Loan;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.Entities.User;
import com.bilbioteca.pruebaTecnicaBiblioteca.data.enums.LoanStatus;
import com.bilbioteca.pruebaTecnicaBiblioteca.repository.BookRepository;
import com.bilbioteca.pruebaTecnicaBiblioteca.repository.LoanRepository;
import com.bilbioteca.pruebaTecnicaBiblioteca.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository,
                       BookRepository bookRepository,
                       UserRepository userRepository) {

        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Loan createLoan(Long userId, Long bookId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        long activeLoans = loanRepository
                .countByUserAndStatus(user, LoanStatus.ACTIVE);

        if(activeLoans >= 3){
            throw new RuntimeException("User already has 3 active loans");
        }

        if(book.getAvailable_copies() <= 0){
            throw new RuntimeException("No copies available");
        }

        Loan loan = new Loan();

        loan.setUser(user);
        loan.setBook(book);

        LocalDate today = LocalDate.now();

        loan.setLoanDate(today);
        loan.setDueDate(today.plusDays(14));
        loan.setStatus(LoanStatus.ACTIVE);

        book.setAvailable_copies(book.getAvailable_copies() - 1);

        bookRepository.save(book);

        return loanRepository.save(loan);
    }
    public Loan returnBook(Long loanId){

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if(loan.getStatus() == LoanStatus.RETURN){
            throw new RuntimeException("Book already returned");
        }

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURN);

        Book book = loan.getBook();

        book.setAvailable_copies(book.getAvailable_copies() + 1);

        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans(){
        return loanRepository.findAll();
    }

    public List<Loan> getUserLoans(User user){
        return loanRepository.findByUser(user);
    }

}
