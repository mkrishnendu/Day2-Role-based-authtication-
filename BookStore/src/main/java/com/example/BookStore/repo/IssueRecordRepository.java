package com.example.BookStore.repo;

import com.example.BookStore.model.IssueRecord;
import com.example.BookStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord,Long> {
}

