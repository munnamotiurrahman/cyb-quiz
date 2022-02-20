package project.cyb.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.cyb.quiz.models.RequestedBook;
import project.cyb.quiz.repository.RequestedBookRepository;

@Service
public class RequestedBookServiceImpl implements RequestedBookService {
    @Autowired
    RequestedBookRepository requestedBookRepository;

    /**
     * To save requestedBook object in database
     */

    @Override
    public void save(RequestedBook requestedBook) {
        requestedBookRepository.save(requestedBook);

    }

    /**
     ** To find a requestedBook object from database
     * 
     */

    @Override
    public Optional<RequestedBook> findById(Long id) {
        return requestedBookRepository.findById(id);
    }

    /**
     * To find all requestedBook objects from database
     * 
     */

    @Override
    public List<RequestedBook> findAll() {
        return requestedBookRepository.findAll();
    }

}
