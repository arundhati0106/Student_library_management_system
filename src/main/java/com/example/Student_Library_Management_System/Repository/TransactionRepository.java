package com.example.Student_Library_Management_System.Repository;

import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Enums.TransactionType;
import com.example.Student_Library_Management_System.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    //get required transaction from JPA using custom query
    //JPA Query lang ->
    @Query("select t from Transaction t where t.card.id=:cardId and t.book.id=:bookId and t.transactionType=:type and t.transactionStatus=:status")
    public List<Transaction> findTransaction(int cardId, int bookId, TransactionType type, TransactionStatus status);

}
//since multiple transactions are possible with same book id, card id -> we save in LIST of transactions
//to get attributes from JPA, eg cardId, bookId ... we use ':' in query

//NATIVE QUERY - SQL
//@Query(value = "select * from Transaction where card.id =:cardId and book.id =:bookId and transactionType =:type and transactionStatus =:status", nativeQuery = true)