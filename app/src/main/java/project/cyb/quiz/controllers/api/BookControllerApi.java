package project.cyb.quiz.controllers.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.cyb.quiz.models.Book;
import project.cyb.quiz.service.BookService;

@RestController
@RequestMapping("/api")
public class BookControllerApi {

    @Autowired
    private BookService bookService;

    @Value("${app.url}")
    private String appURL;

    /**
     * showAllBooks is a GetMapping api function which shows all books
     * API url:-> localhost:8027/api/showBooks
     * 
     * Dummy request body: null
     * 
     * 
     * Dummy respose body:
     * [
     * {
     * "id": 1,
     * "type": "CSE",
     * "name": "Dummy book",
     * "author": "Mr. X Uddin",
     * "language": "English",
     * "fileType": null,
     * "numberOfBooks": 10,
     * "isAvailable": true,
     * "filefileData": null
     * },
     * {
     * "id": 2,
     * "type": "Bank",
     * "name": "Pattrol",
     * "author": "Mr. X Uddin",
     * "language": "Bangla",
     * "fileType": null,
     * "numberOfBooks": 10,
     * "isAvailable": true,
     * "filefileData": null
     * },
     * {
     * ]
     * 
     * 
     * @return
     */

    @GetMapping("/showBooks")
    public List<Book> showAllBooks() {
        List<Book> books = bookService.findAll();
        return books;
    }

    /**
     * addBookPost is a Post Mapping api Function for book add request
     * 
     * Api url :-> localhost:8027/api/addBook
     * 
     * Dummy request body:
     * {
     * "name": "Api tutorial",
     * "language": "English",
     * "numberOfBooks": "13",
     * "type": "type",
     * "writterName": "Mr. Hello"
     * }
     * 
     * Dummy response body:
     * 
     * {
     * "book object": {
     * "id": 9,
     * "type": "type",
     * "name": "Api tutorial",
     * "author": "Mr. Hello",
     * "language": "English",
     * "fileType": null,
     * "numberOfBooks": 13,
     * "isAvailable": true,
     * "filefileData": null
     * },
     * "message": "New book added",
     * "status": "Sueccess!"
     * }
     * 
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
    public HashMap<String, Object> addBookPost(@RequestBody String BookJson)

            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(BookJson);
        String bookName = rootNode.get("name").asText();
        String language = rootNode.get("language").asText();
        String bookType = rootNode.get("type").asText();
        String writterName = rootNode.get("writterName").asText();
        String numberOfBooks = rootNode.get("numberOfBooks").asText();

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

        bookService.save(books);
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", "Sueccess!");
        response.put("message", "New book added");
        response.put("book object", books);
        return response;
    }

    // /**
    // * * shareBookPost is a Post Mapping Function for requesting to share a book
    // by
    // * giving book id
    // *
    // * @param id
    // * @param model
    // * @return
    // */

    // @PostMapping("/shareBook")
    // public String shareBookPost(@RequestParam(name = "id", required = true,
    // defaultValue = "") String id, Model model) {
    // Optional<Book> book = bookService.findById(Long.parseLong(id));
    // model.addAttribute("book", book);
    // model.addAttribute("id", id);
    // model.addAttribute("bookDetailsURL", "/bookDetails/" + id);
    // return "share-book/share-book";
    // }

    /**
     * bookDetailsGet is a Get Mapping Api function to get a specific book details
     * 
     * Api url:-> localhost:8027/api/bookDetails/{id}
     * example:-> localhost:8027/api/bookDetails/3
     * 
     * Dummy request body: null
     * 
     * Dummy response body:
     * {
     * "id": 3,
     * "type": "Bank",
     * "name": "Pattrol",
     * "author": "Mr. X Uddin",
     * "language": "Bangla",
     * "fileType": null,
     * "numberOfBooks": 10,
     * "isAvailable": true,
     * "filefileData": null
     * }
     * 
     * @param id
     * @return
     */

    @GetMapping("bookDetails/{id}")
    public Book bookDetailsGet(@PathVariable Long id) {
        Optional<Book> books = bookService.findById(id);
        return books.get();
    }

}
