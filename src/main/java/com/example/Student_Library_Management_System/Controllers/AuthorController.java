package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.AuthorRequestDTO;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDTO;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author") //can write w/o '/'
public class AuthorController {
    @Autowired
    AuthorService authorService;

//... using author entity ...//
//    @PostMapping("/add")
//    public String addAuthor(@RequestBody Author author) {
//        return authorService.addAuthor(author);
//    }

//... using author DTO ...//
    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return authorService.addAuthor(authorRequestDTO);
    }

    //will result in infinite recursion
    //as author has book obj, book has author obj... keep calling each other -> stack overflow error
//    @GetMapping("getAuthor")
//    public Author getAuthor(@RequestParam("authorId") Integer authorId) {
//        return authorService.getAuthor(authorId);
//    }

    @GetMapping("getAuthor")
    public AuthorResponseDTO getAuthor(@RequestParam("authorId") Integer authorId) {
        return authorService.getAuthor(authorId);
    }
}
