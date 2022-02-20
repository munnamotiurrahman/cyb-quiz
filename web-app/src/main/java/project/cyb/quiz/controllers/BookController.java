package project.cyb.quiz.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.cyb.quiz.models.Book;
import project.cyb.quiz.models.RequestedBook;
import project.cyb.quiz.service.BookService;
import project.cyb.quiz.service.RequestedBookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private RequestedBookService requestedBookService;

    @Value("${app.url}")
    private String appURL;

    @GetMapping("/addBook")
    public String addBook() {
        return "book/add-book";
    }

    /**
     * showAllBooks is a GetMapping function which shows all books
     * 
     * @param model
     * @return
     */
    @GetMapping("/showBooks")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book/show-books";
    }

    /**
     * PostMapping is a Post Mapping Function for book add request
     * 
     * @param multipartFile
     * @param bookName
     * @param language
     * @param bookType
     * @param writterName
     * @param numberOfBooks
     * @return
     * @throws IOException
     */

    @PostMapping("/addBook")
    public String addBookPost(
            @RequestParam(name = "name", required = true, defaultValue = "") String bookName,
            @RequestParam(name = "language", required = true, defaultValue = "") String language,
            @RequestParam(name = "type", required = true, defaultValue = "") String bookType,
            @RequestParam(name = "writterName", required = true, defaultValue = "") String writterName,
            @RequestParam(name = "numberOfBooks", required = true, defaultValue = "") String numberOfBooks)
            throws IOException {

        Book books = new Book();
        books.setLanguage(language);
        books.setName(bookName);
        books.setType(bookType);
        books.setAuthor(writterName);
        int numberOfBooksInt = Integer.parseInt(numberOfBooks);
        books.setNumberOfBooks(numberOfBooksInt);
        if (numberOfBooksInt > 0) {
            books.setIsAvailable(true);
        } else {
            books.setIsAvailable(false);
        }

        // byte[] bytes = multipartFile.getBytes();
        // String encodedFileData = Base64.getEncoder().encodeToString(bytes);
        // books.setFileType(multipartFile.getContentType());
        // books.setFileData(encodedFileData);
        bookService.save(books);

        return "book/success";
    }

    /**
     * makeAvailable is a get Mapping function to make available a book
     * 
     * @return
     */

    @GetMapping("/makeAvailable")
    public String makeAvailable() {
        return "make-available/make-available";
    }

    /**
     * makeAvailable is a Post Mapping function for requesting to available a book
     * 
     * @param id
     * @param model
     * @return
     */

    @PostMapping("/makeAvailable")
    public String makeAvailablePost(@RequestParam(name = "id", required = true, defaultValue = "") String id,
            Model model) {
        Optional<RequestedBook> requestedBook = requestedBookService.findById(Long.parseLong(id));
        requestedBook.get().setIsAvailable(true);
        requestedBookService.save(requestedBook.get());
        return "redirect:showRequestedBooks/";
    }

    /**
     * shareBook is a Get Mapping Function for sharing a book
     * 
     * @param model
     * @return
     */
    // @GetMapping("/shareBook")
    // public String shareBook(Model model) {
    // model.addAttribute("appUrl", appURL + "/bookDetails/" + 1);
    // return "share-book/share-book";
    // }

    /**
     * * shareBookPost is a Post Mapping Function for requesting to share a book by
     * giving book id
     * 
     * @param id
     * @param model
     * @return
     */

    @PostMapping("/shareBook")
    public String shareBookPost(@RequestParam(name = "id", required = true, defaultValue = "") String id, Model model) {
        Optional<Book> book = bookService.findById(Long.parseLong(id));
        model.addAttribute("book", book);
        model.addAttribute("id", id);
        model.addAttribute("bookDetailsURL", "/bookDetails/" + id);
        return "share-book/share-book";
    }

    /**
     * bookDetailsGet is a Get Mapping function to get a specific book details
     * 
     * @param id
     * @param model
     * @return
     */

    @GetMapping("bookDetails/{id}")
    public String bookDetailsGet(@PathVariable Long id, Model model) {
        Optional<Book> books = bookService.findById(id);
        model.addAttribute("bookName", "Book's Name: " + books.get().getName());
        model.addAttribute("writerName", "Writer's Name: " + books.get().getAuthor());
        model.addAttribute("language", "Language: " + books.get().getLanguage());
        model.addAttribute("id", books.get().getId());
        if (books.get().getIsAvailable()) {
            model.addAttribute("available", "The book is available in Nilkhet");
        }

        return "book/book-details";
    }

}
