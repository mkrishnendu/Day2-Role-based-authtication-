package com.example.BookStore.service;


import com.example.BookStore.model.Book;
import com.example.BookStore.model.IssueRecord;
import com.example.BookStore.model.User;
import com.example.BookStore.repo.BookRepository;
import com.example.BookStore.repo.IssueRecordRepository;
import com.example.BookStore.repo.UserRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class IssueRecordService {
    @Autowired
    private IssueRecordRepository issueRecordRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRecordRepository userRecordRepository;


    public IssueRecord issueTheBook(Long bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));

        if((book.getQuantity()<=0) || !book.getIsAvailable()){
            throw new RuntimeException("book is not avalable");
        }
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRecordRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("user not found"));

        IssueRecord issueRecord=new IssueRecord();
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setIsReturned(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);
        book.setQuantity(book.getQuantity()-1);
        if(book.getQuantity()==0){
            book.setIsAvailable(false);
        }
        bookRepository.save(book);
       return issueRecordRepository.save(issueRecord);

    }

    public IssueRecord returnTheBook(Long issueRecordId) {
        IssueRecord issueRecord = issueRecordRepository.findById(issueRecordId).orElseThrow(()->new RuntimeException("issue record Not found"));


        if (issueRecord.getIsReturned()) {
            throw new RuntimeException("Book is already returned");
        }

        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity() + 1);
        book.setIsAvailable(true);
        bookRepository.save(book);

        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsReturned(true);
        issueRecordRepository.save(issueRecord);
        return issueRecord;
    }
}
