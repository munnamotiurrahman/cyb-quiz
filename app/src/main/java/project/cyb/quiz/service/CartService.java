package project.cyb.quiz.service;

import java.util.List;
import java.util.Optional;

import project.cyb.quiz.models.Cart;

public interface CartService {
    /**
     * To save book object in book table
     * 
     * @param book
     */
    void save(Cart cart);

    /**
     * To find by id form book table
     * 
     * @param id
     * @return
     */

    Optional<Cart> findById(Long id);

    /**
     * To find all object from book table
     * 
     * @return
     */
    List<Cart> findAll();

}
