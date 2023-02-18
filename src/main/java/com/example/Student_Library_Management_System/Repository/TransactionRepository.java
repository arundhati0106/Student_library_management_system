package com.example.Student_Library_Management_System.Repository;

import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Enums.TransactionType;
import com.example.Student_Library_Management_System.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // sort on the basis of time to get the latest issue date
    @Query("select t from Transaction t where t.card.id=:cardId and t.book.id=:bookId and t.transactionType=:type and t.transactionStatus=:status")
    public List<Transaction> findTransaction(int cardId, int bookId, TransactionType type, TransactionStatus status);

}