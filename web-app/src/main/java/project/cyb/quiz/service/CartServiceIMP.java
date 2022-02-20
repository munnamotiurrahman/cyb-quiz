package project.cyb.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.cyb.quiz.models.Cart;
import project.cyb.quiz.repository.CartRepository;

@Service
public class CartServiceIMP implements CartService {
    @Autowired
    CartRepository cartRepository;

    /**
     * To save a book object
     */
    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    /**
     * To find by id form book table
     */

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    /**
     * To find all object from book table
     */

    @Override
    public List<Cart> findAll() {
        List<Cart> cart = cartRepository.findAll();
        return cart;
    }
}
