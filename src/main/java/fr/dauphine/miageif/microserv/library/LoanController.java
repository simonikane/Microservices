package fr.dauphine.miageif.microserv.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {
    @Autowired
    private Environment environment;
    // se charge de la creation de l'instance

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private  BookRepository bookRepository;

    @PostMapping("/library/save_loan/{book_id}/{reader_id}")
    private void saveLoan(@PathVariable("book_id") Long book_id,
                          @PathVariable("reader_id") Long  reader_id){
        Book book = bookRepository.getOne(book_id);
        Reader reader = readerRepository.getOne(reader_id);
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setReader(reader);

        loanRepository.save(loan);

    }
}
