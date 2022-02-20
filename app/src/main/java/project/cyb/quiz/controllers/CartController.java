package project.cyb.quiz.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.cyb.quiz.models.Book;
import project.cyb.quiz.models.Cart;
import project.cyb.quiz.models.User;
import project.cyb.quiz.service.BookService;
import project.cyb.quiz.service.CartService;
import project.cyb.quiz.service.UserService;

@Controller
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CartService cartService;

    @Value("${app.url}")
    private String appURL;

    /**
     * PostMapping is a Post Mapping Function for book add request
     * 
     * @param bookId
     * @return
     * @throws IOException
     */

    @PostMapping("/addToCart")
    public String addBookPost(
            @RequestParam(name = "book_id", required = true, defaultValue = "") Long bookId,
            Authentication authentication, Model model)
            throws IOException {
        Optional<User> optionalUser = userService.findById(Long.parseLong(authentication.getName()));
        if (!optionalUser.isPresent()) {
            return "error";
        }
        User user = optionalUser.get();
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (!optionalBook.isPresent()) {
            return "error";
        }
        Book book = optionalBook.get();
        Cart cart = user.getCart();
        List<Book> books = cart.getBooks();
        books.add(book);
        cart.setBooks(books);
        cartService.save(cart);
        model.addAttribute("message", "Book added to cart successfully!");
        return "success";
    }
}
