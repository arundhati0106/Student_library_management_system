package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDTO;
import com.example.Student_Library_Management_System.DTOs.ReturnBookRequestDTO;
import com.example.Student_Library_Management_System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("issueBook")
    public String issueBook(@RequestBody IssueBookRequestDTO issueBookRequestDTO) {
        try {
            return transactionService.issueBook(issueBookRequestDTO);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("returnBook")
    public String returnBook(@RequestBody ReturnBookRequestDTO returnBookRequestDTO) {
        try {
            return transactionService.returnBook(returnBookRequestDTO);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
}
