package library.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"library.book","library.reader"})
//Test
public class LoanService {

    public static void main(String[] args) {
        SpringApplication.run(LoanService.class, args);
    }

}
