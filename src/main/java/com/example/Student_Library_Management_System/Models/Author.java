package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.BookGenre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String country;

    //bidirectional mapping
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL) //Author: Book :: One: Many
    //@JsonIgnore / @JsonIgnoreProperties -> ignore recursion calls

    //many child class entities are associated with one parent class object, so a list of child class object
    private List<Book> booksWritten;
    //this attribute is not set while adding an author, it's set when a book
    //is added, written by this author then this attribute needs to be updated

    //constructors
    public Author() {
        //initialise the list of books written by the author
        //spring would have done it automatically (while creating bean), so no null pointer exception, but good practise
        booksWritten = new ArrayList<>();
    }

    //getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public List<Book> getBooksWritten() { return booksWritten; }
    public void setBooksWritten(List<Book> booksWritten) { this.booksWritten = booksWritten; }
}
