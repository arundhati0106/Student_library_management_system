package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDTO;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Enums.TransactionType;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transaction;
import com.example.Student_Library_Management_System.Repository.BookRepository;
import com.example.Student_Library_Management_System.Repository.CardRepository;
import com.example.Student_Library_Management_System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {
        //extract values from DTO object
        int bookId = issueBookRequestDTO.getBookId();
        int cardId = issueBookRequestDTO.getCardId();

        //get book and card entity with resp ids... to save it in transaction repo
        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        //repo deals with entity, so create object
        Transaction transaction = new Transaction();

        //set basic attributes of entity layers
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.ISSUE);

        //save transaction record even if status is not success -> eg: money transactions are recorded, GPAY
        //mandatory validation or checks

        //1. book doesnt exist, or book is already issued
        if(book == null || book.isIssued() == true) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }

        //2. card is not valid
        if(card == null || !card.getCardStatus().equals(CardStatus.ACTIVATED)) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not valid");
        }

        //transaction success -> save
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //set attributes of book
        //1. set book as issued
        book.setIssued(true);

        //2. update list of transactions made on this book
        List<Transaction> listOfTransactionsMadeOnBook = book.getListOfTransactions();
        listOfTransactionsMadeOnBook.add(transaction);
        book.setListOfTransactions(listOfTransactionsMadeOnBook);

        //set attributes of card
        //1. update list of books issued by this card... Book and Card
        List<Book> issuedBooksByCard = card.getBooksIssued(); //get
        issuedBooksByCard.add(book);                          //add
        card.setBooksIssued(issuedBooksByCard);               //save or update

        //2. update list of transactions made by this card... Card and Transaction
        List<Transaction> transactionListForCard = card.getTransactionList(); //get
        transactionListForCard.add(transaction);                              //add
        card.setTransactionList(transactionListForCard);                      //save or update

        //update
        //only card -> parent of both book, transaction, so saved by cascading
        cardRepository.save(card);

        return "transaction recorded";
    }
}
