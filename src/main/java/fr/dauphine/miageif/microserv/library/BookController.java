package fr.dauphine.miageif.microserv.library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;
import java.net.URI;

@RestController
public class BookController {
    // String se charge de la creation de l'instance
    @Autowired
    private Environment environment;
    // se charge de la creation de l'instance

    @Autowired
    private BookRepository repository;
    @GetMapping("/library/book/{ISBN}")
    public Book findByISBN(@PathVariable String ISBN){
        Book book = repository.findByISBN(ISBN);
        return book;
    }

    @GetMapping("/library/book_by_author/{author}")
    public List<Book> findByAuthor(@PathVariable String author){
        return repository.findByAuthor(author);
    }

}
