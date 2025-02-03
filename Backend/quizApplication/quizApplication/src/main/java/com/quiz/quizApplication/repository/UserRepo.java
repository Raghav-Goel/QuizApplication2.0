package com.quiz.quizApplication.repository;

import com.quiz.quizApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    public Optional<User> findByEmailId(String emailId);
}
