package project.cyb.quiz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import project.cyb.quiz.models.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
