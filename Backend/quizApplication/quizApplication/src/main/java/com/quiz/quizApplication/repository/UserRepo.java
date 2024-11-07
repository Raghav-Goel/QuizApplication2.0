package com.quiz.quizApplication.repository;

import com.quiz.quizApplication.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {
    public Optional<User> findByEmailId(String emailId);
}
