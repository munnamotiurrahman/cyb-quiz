package project.cyb.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import project.cyb.quiz.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

    /**
     * To find a book object from database
     */
    Optional<Book> findById(Long id);

    /**
     * To find all book objects from database
     * 
     */

    List<Book> findAll();
}
