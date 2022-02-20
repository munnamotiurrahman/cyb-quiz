package project.cyb.quiz.controllers.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.cyb.quiz.models.RequestedBook;
import project.cyb.quiz.service.RequestedBookService;

@RestController
@RequestMapping("/api")
public class RequestedBookControllerApi {

    @Autowired
    RequestedBookService requestedBookService;

    /**
     * addRequestedBookPost is a Post Mapping api Function for book add request
     * 
     * Api url:-> localhost:8027/api/addRequestedBook
     * 
     * Dummy request body:
     * {
     * "name": "Api tutorial",
     * "language": "English",
     * "type": "type",
     * "writterName": "Mr. Hello"
     * }
     * 
     * Dummy response body:
     * {
     * "book object": {
     * "id": 1,
     * "type": "type",
     * "name": "Api tutorial",
     * "author": "Mr. Hello",
     * "language": "English",
     * "isAvailable": false
     * },
     * "message": "New book added",
     * "status": "Sueccess!"
     * }
     * 
     * @param BookJson
     * @return
     * @throws IOException
     */

    @PostMapping("/addRequestedBook")
    public HashMap<String, Object> addRequestedBookPost(@RequestBody String BookJson)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(BookJson);
        String bookName = rootNode.get("name").asText();
        String language = rootNode.get("language").asText();
        String bookType = rootNode.get("type").asText();
        String writterName = rootNode.get("writterName").asText();

        RequestedBook requestedBook = new RequestedBook();
        requestedBook.setLanguage(language);
        requestedBook.setName(bookName);
        requestedBook.setType(bookType);
        requestedBook.setAuthor(writterName);

        requestedBookService.save(requestedBook);
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", "Sueccess!");
        response.put("message", "New book added");
        response.put("book object", requestedBook);
        return response;
    }

    /**
     * showAllRequestedBooks is a GetMapping api function which shows all requested
     * books
     * 
     * API url: localhost:8027/api/showRequestedBooks
     * 
     * Request body: null
     * 
     * 
     * Response body:
     * [
     * {
     * "id": 1,
     * "type": "type",
     * "name": "Api tutorial",
     * "author": "Mr. Hello",
     * "language": "English",
     * "isAvailable": false
     * }
     * ]
     * 
     * @param model
     * @return
     */

    @GetMapping("/showRequestedBooks")
    public List<RequestedBook> showAllRequestedBooks(Model model) {
        List<RequestedBook> requestedBooks = requestedBookService.findAll();
        model.addAttribute("requestedBooks", requestedBooks);
        return requestedBooks;
    }
}
