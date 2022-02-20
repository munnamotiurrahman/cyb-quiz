package project.cyb.quiz.service;

import java.util.Optional;

import project.cyb.quiz.models.User;

public interface UserService {
    /**
     * To save user data to user table
     * 
     * @param User
     * @return
     */
    User save(User User);

    /**
     * To find by id from user table
     * 
     * @param userId
     * @return
     */

    Optional<User> findById(Long userId);

    /**
     * To find all object from user table
     * 
     * @param email
     * @return
     */

    Optional<User> findByEmail(String email);
}
