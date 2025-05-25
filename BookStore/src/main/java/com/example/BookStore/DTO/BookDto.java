package com.example.BookStore.DTO;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
    private Boolean isAvailable;


}