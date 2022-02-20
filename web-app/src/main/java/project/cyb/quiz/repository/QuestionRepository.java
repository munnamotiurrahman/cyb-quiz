package project.cyb.quiz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import project.cyb.quiz.models.Questions;

public interface QuestionRepository extends CrudRepository<Questions, Long> {

    /**
     * To find a Question object from database
     */
    Optional<Questions> findById(Long id);

    /**
     * To find all Question objects from database
     * 
     */

    List<Questions> findAll();
}
