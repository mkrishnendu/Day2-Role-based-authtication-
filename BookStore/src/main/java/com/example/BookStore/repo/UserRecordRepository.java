package com.example.BookStore.repo;


import com.example.BookStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRecordRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
