package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author") //can write w/o '/'
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }
}
