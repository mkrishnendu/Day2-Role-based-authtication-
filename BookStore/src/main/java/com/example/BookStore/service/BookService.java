package com.example.BookStore.service;

import com.example.BookStore.DTO.BookDto;
import com.example.BookStore.model.Book;
import com.example.BookStore.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookbyId(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book addbooks(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setIsAvailable(bookDto.getIsAvailable());
        book.setQuantity(bookDto.getQuantity());
        return bookRepository.save(book);
    }

    public Book updateBook(BookDto bookDto, long id) {
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
       oldBook.setTitle(bookDto.getTitle());
        oldBook.setAuthor(bookDto.getAuthor());
        oldBook.setAuthor(bookDto.getAuthor());
        oldBook.setIsbn(bookDto.getIsbn());
        oldBook.setIsAvailable(bookDto.getIsAvailable());
        oldBook.setQuantity(bookDto.getQuantity());
        return bookRepository.save(oldBook);
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }
}
