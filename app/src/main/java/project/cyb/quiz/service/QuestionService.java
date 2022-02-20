package project.cyb.quiz.service;

import java.util.List;
import java.util.Optional;

import project.cyb.quiz.models.Questions;

public interface QuestionService {
    /**
     * To save Question object in database
     * 
     * @param Questions
     */
    void save(Questions question);

    /**
     * To find a Question object from database
     * 
     * @param id
     * @return
     */

    Optional<Questions> findById(Long id);

    /**
     * To find all Question objects from database
     * 
     * @return
     */

    List<Questions> findAll();

    void deleteById(Long id);
}
