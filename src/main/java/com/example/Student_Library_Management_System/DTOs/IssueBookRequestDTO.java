package com.example.Student_Library_Management_System.DTOs;

public class IssueBookRequestDTO {
    private int bookId;
    private int cardId;
    //student associated with card, so separate attribute not reqd

    //constructors
    public IssueBookRequestDTO() {

    }

    //getters and setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getCardId() { return cardId; }
    public void setCardId(int cardId) { this.cardId = cardId; }
}
