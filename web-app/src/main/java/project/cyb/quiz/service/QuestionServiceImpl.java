package project.cyb.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.cyb.quiz.models.Questions;
import project.cyb.quiz.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    /**
     * To save question object in database
     */

    @Override
    public void save(Questions question) {
        questionRepository.save(question);
    }

    /**
     ** To find a question object from database
     * 
     */

    @Override
    public Optional<Questions> findById(Long id) {
        return questionRepository.findById(id);
    }

    /**
     * To find all question objects from database
     * 
     */

    @Override
    public List<Questions> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

}