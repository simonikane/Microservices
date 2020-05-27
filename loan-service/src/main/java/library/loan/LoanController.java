package library.loan;

import library.book.Book;
import library.book.BookRepository;
import library.reader.Reader;
import library.reader.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

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
    private BookRepository bookRepository;

    @PostMapping("/library/save_loan")
    private String saveLoan(@RequestBody Map<String,Object> bodyContent){

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
                loan.setAvailableLoan(false);
                loanRepository.save(loan);
            }
        }
        //TODO: Change the renturn
        return "Ok";
    }

    private boolean loanCanBeAddToDataBase(Loan loan, LoanRepository loanRepository){
        List<Loan> loans = loanRepository.findByAvailableLoanAndBookId(false,loan.getBook().getId());
        for (Loan loan_iterate:
                loans) {
            if ((loan_iterate.isBetweenDatesLoanAndReturn(loan.getLoanDate()) ||
                    loan_iterate.isBetweenDatesLoanAndReturn(loan.getReturnDate()))) {
                return false;
            }
        }

        return true;
    }

    @PostMapping("/library/return_loans")
    public String returnLoan(@RequestBody Map<String,Object> bodyContent){
        List listBookIds = (List) bodyContent.get("book_id");

        //
        Date dateReturn = new Date(System.currentTimeMillis());
        for(Object bookIdObject : listBookIds){
            Long bookId = Long.valueOf( (int) bookIdObject);
            loanRepository.
                    findByAvailableLoanAndBookId(false,bookId).
                    stream().forEach((loan) -> {loan.setAvailableLoan(true);
                                                loan.setReturnDate(dateReturn);
                                                loanRepository.save(loan);});
        }
        return "OK";
    }

    @GetMapping("/library/loan_book/{book_id}")
    public List<Loan> loanBook(@PathVariable Long book_id){
        return loanRepository.findByBookId(book_id);
    }

    @GetMapping("/library/loan_reader/{reader_id}")
    public List<Loan> loanReader(@PathVariable Long reader_id){
        return loanRepository.findByReaderId(reader_id);
    }
}
