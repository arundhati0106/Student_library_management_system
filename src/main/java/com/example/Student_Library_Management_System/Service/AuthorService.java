package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.DTOs.AuthorRequestDTO;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

//    public String addAuthor(Author author) {
//        authorRepository.save(author);
//        return "Author added successfully.";
//    }

//... using author DTO ...//
    public String addAuthor(AuthorRequestDTO authorRequestDTO) {
        //in the params -> object is of type DTO
        //repo interacts only with entity objects...

        //so convert authorEntryDTO to author entity

        //1. create author object
        Author author = new Author();

        //2. set attributes, to set correct values in db
        //extract values from DTO object, and set values in entity obj
        author.setName(authorRequestDTO.getName());
        author.setAge(authorRequestDTO.getAge());
        author.setCountry(authorRequestDTO.getCountry());

        //save changes
        authorRepository.save(author);
        return "Author added successfully.";
    }
}
