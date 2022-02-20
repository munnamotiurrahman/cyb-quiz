package project.cyb.quiz.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.cyb.quiz.models.RequestedBook;
import project.cyb.quiz.service.RequestedBookService;

@Controller
public class RequestedBookController {

    @Autowired
    RequestedBookService requestedBookService;

    @GetMapping("/addRequestedBook")
    public String addRequestedBookGet() {
        return "request-book/request-book";
    }

    @PostMapping("/addRequestedBook")
    public String addRequestedBookPost(
            // @RequestParam("filedata") MultipartFile multipartFile,
            @RequestParam(name = "name", required = true, defaultValue = "") String bookName,
            @RequestParam(name = "language", required = true, defaultValue = "") String language,
            @RequestParam(name = "type", required = true, defaultValue = "") String bookType,
            @RequestParam(name = "writterName", required = true, defaultValue = "") String writterName,
            @RequestParam(name = "numberOfBooks", required = true, defaultValue = "") String numberOfBooks)
            throws IOException {

        RequestedBook requestedBook = new RequestedBook();
        requestedBook.setLanguage(language);
        requestedBook.setName(bookName);
        requestedBook.setType(bookType);
        requestedBook.setAuthor(writterName);

        requestedBookService.save(requestedBook);
        return "request-book/success";
    }

    @GetMapping("/showRequestedBooks")
    public String showAllBooks(Model model) {
        List<RequestedBook> requestedBooks = requestedBookService.findAll();
        model.addAttribute("requestedBooks", requestedBooks);
        return "request-book/show-requestedBooks";
    }
}
