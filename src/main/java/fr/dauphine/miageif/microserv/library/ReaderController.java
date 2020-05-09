package fr.dauphine.miageif.microserv.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ReaderController {
    // String se charge de la creation de l'instance
    @Autowired
    private Environment environment;
    // se charge de la creation de l'instance

    @Autowired
    private ReaderRepository repository;
    @GetMapping("/library/reader_by_name/{name}")
    public List<Reader> findByName(@PathVariable String name){
        return repository.findByName(name);
    }
    @GetMapping("/library/reader_by_first_name/{firstName}")
    public List<Reader> findByFirstName(@PathVariable String firstName){
        return repository.findByFirstName(firstName);
    }

    @GetMapping("/library/reader_by_gender/{gender}")
    public List<Reader> findByGender(@PathVariable String gender){
        return repository.findByGender(gender);
    }

    @GetMapping("/library/reader_by_birth_date/{birthDate}")
    public List<Reader> findByBirthDate(@PathVariable Date birthDate){
        return repository.findByBirthDate(birthDate);
    }

    @PostMapping("/library/save_reader")
    private Long saveReader(@RequestBody Reader reader){
        repository.save(reader);
        return reader.getId();
    }

    @PostMapping("/library/update_reader")
    private String updateReader(@RequestBody Reader reader){
        if(repository.existsById(reader.getId())){
            repository.save(reader);
            return "OK";
        }
        return "KO";
    }

    @DeleteMapping("/library/delete_reader/{id}")
    private void deleteReader(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping("/library/find_all_readers")
    private List<Reader> findAllReaders(){return repository.findAll();}
}
