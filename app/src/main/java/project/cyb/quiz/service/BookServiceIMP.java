package project.cyb.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.cyb.quiz.models.Book;
import project.cyb.quiz.repository.BookRepository;

@Service
public class BookServiceIMP implements BookService {
    @Autowired
    BookRepository bookRepository;

    /**
     * To save a book object
     */
    @Override
    public void save(Book books) {
        bookRepository.save(books);
    }

    /**
     * To find by id form book table
     */

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * To find all object from book table
     */

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        return books;
    }
}
