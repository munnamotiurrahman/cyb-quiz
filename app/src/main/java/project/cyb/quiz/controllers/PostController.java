package project.cyb.quiz.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.cyb.quiz.models.Post;
import project.cyb.quiz.models.User;
import project.cyb.quiz.service.PostService;
import project.cyb.quiz.service.UserService;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/addPost")
    public String addPost() {
        return "post/add-post";
    }

    @PostMapping("/addPost")
    public String addBookPost(
            @RequestParam(name = "title", required = true, defaultValue = "") String title,
            @RequestParam(name = "body", required = true, defaultValue = "") String body, Authentication authentication)
            throws IOException {
        Optional<User> optionalUser = userService.findById(Long.parseLong(authentication.getName()));
        if (!optionalUser.isPresent()) {
            return "error";
        }
        User user = optionalUser.get();
        Post post = new Post();
        post.setContent(body);
        post.setTitle(title);
        post.setCreatedOn(LocalDateTime.now());
        post.setUser(user);
        postService.save(post);
        return "post/success";
    }
}
