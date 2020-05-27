package library.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByISBN(String ISBN);

    List<Book> findByAuthor(String author);

}
