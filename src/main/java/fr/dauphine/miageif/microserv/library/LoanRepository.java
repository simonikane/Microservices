package fr.dauphine.miageif.microserv.library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByBookId(Long bookId);
    List<Loan> findByAvailableLoanAndBookId(Boolean availableId, Long bookId);
    List<Loan> findByReaderId(Long readerId);
}
