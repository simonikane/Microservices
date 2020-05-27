package library.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"library.reader","library.book","library.loan"})
@SpringBootApplication
public class LoanService {

    public static void main(String[] args) {
        SpringApplication.run(LoanService.class, args);
    }

}
