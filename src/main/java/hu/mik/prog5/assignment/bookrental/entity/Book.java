package hu.mik.prog5.assignment.bookrental.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer pageNumber;
    private Genre genre;
    private String rentedBy;
    private LocalDate rentedUntil;
    private Boolean isRented;

}
