package com.Library.libraryManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;



@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "title" , unique = true)
    @NotEmpty(message = "title must not be empty")
    private String title;
    @NotEmpty(message = "author must not be empty")
    private String author;
    @Min(value = 1500 ,message = "Invalid Year")
    @Max(value = 2024 ,message = "Invalid Year")
    private String publicationYear;
    @NotEmpty(message = "isbn must be valid")
    private String isbn;


    public Book() {
    }

    public Book(long id, String title, String author, String publicationYear, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
