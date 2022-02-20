package project.cyb.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.cyb.quiz.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
