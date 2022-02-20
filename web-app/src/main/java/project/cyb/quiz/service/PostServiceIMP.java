package project.cyb.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.cyb.quiz.models.Post;
import project.cyb.quiz.repository.PostRepository;

@Service
public class PostServiceIMP implements PostService {
    @Autowired
    PostRepository postRepository;

    /**
     * To save a post object
     */
    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    /**
     * To find by id form post table
     */

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    /**
     * To find all object from post table
     */

    @Override
    public List<Post> findAll() {
        List<Post> post = postRepository.findAll();
        return post;
    }
}
