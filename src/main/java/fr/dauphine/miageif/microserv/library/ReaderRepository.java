package fr.dauphine.miageif.microserv.library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader,Long> {

    List<Reader> findByName(String name);

    List<Reader> findByGender(String gender);

    List<Reader> findByFirstName(String firstName);

    List<Reader> findByBirthDate(Date birthDate);

}
