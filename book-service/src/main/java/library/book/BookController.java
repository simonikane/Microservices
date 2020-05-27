package library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    // String se charge de la creation de l'instance
    @Autowired
    private Environment environment;
    // se charge de la creation de l'instance

    @Autowired
    private BookRepository repository;
    @GetMapping("/library/book_by_isbn/{ISBN}")
    public Book findByISBN(@PathVariable String ISBN){
        Book book = repository.findByISBN(ISBN);
        return book;
    }

    @GetMapping("/library/book_by_author/{author}")
    public List<Book> findByAuthor(@PathVariable String author){
        return repository.findByAuthor(author);
    }

    @PostMapping("/library/save_book")
    private String saveBook(@RequestBody Book book){
        repository.save(book);
        return book.getISBN();
    }

    @PostMapping("/library/update_book")
    private String updateBook(@RequestBody Book book){
        if(repository.existsById(book.getId())){
            repository.save(book);
            return "OK";
        }
        return "KO";
    }

    @DeleteMapping("/library/delete_book/{id}")
    private void deleteBook(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping("/library/find_all_books")
    private List<Book> findAllBooks(){return repository.findAll();}

}
