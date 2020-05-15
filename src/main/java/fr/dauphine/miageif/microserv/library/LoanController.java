package fr.dauphine.miageif.microserv.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/library/save_loan")
    private String saveLoan(@RequestBody Map<String,Object> bodyContent){

        //TODO : Recupérer from JSON
        List listBookIds = (List) bodyContent.get("book_id");
        Long readerId = Long.valueOf((int) bodyContent.get("reader_id"));


        Date loanDate = new Date();
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            loanDate = formatter.parse((String) bodyContent.get("loan_date") );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //adding two weeks
        Calendar calendar = Calendar.getInstance(); calendar.setTime(loanDate); calendar.add(Calendar.WEEK_OF_MONTH,2);
        Date returnDate = calendar.getTime();

        for (Object bookIdObject:
            listBookIds) {

            Long bookId = Long.valueOf((int) bookIdObject);
            Book book = bookRepository.getOne(bookId);
            Reader reader = readerRepository.getOne(readerId);

            //create loan instantion
            Loan loan = new Loan(reader,book,loanDate,returnDate);

            if(loanCanBeAddToDataBase(loan,loanRepository)){
                loanRepository.save(loan);
            }
        }

        return "Ok";
    }

    private boolean loanCanBeAddToDataBase(Loan loan, LoanRepository loanRepository){
        List<Loan> loans = loanRepository.findByBookId(loan.getBook().getId());
        System.out.println(loans);
        for (Loan loan_iterate:
                loans) {
            if( loan_iterate.isBetweenDatesLoanAndReturn(loan.getLoanDate()) ||
                    loan_iterate.isBetweenDatesLoanAndReturn(loan.getReturnDate())){
                System.out.println(loan.getLoanDate() + "ne peut pas être ajouté");
                return false;
            }
        }
        System.out.println(loan.getLoanDate() + "peut être ajouté");
        return true;
    }
}
