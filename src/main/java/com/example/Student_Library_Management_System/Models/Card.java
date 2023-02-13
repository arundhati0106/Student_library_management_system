package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "card")
public class Card {
/*
plan -> save card in database
before saving, set all of it's attributes...
att #1 -> id, auto generated, need not be set
att #2 -> createdOn, auto generated, need not be set
att #3 -> updatedOn, auto generated, need not be set
att #4 -> cardStatus, set in student service
att #5 -> student, foreign key
*/

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp //auto timestamps the time when an entry is created
    private Date createdOn;    //util.Date -> has a detailed Time Stamp : DD:MM:YYYY:HH:MM:SS:MS

    @UpdateTimestamp   //sets time to when entry was last updated
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING) //sets foreign key
    private CardStatus cardStatus;

    //unidirectional mapping
    @OneToOne
    @JoinColumn
    //make an object of parent entity, to which connection is to be made with
    //student -> variable name of Parent class entity, foreign key attribute
    private Student student; //used in parent class, for bidirectional mapping

    //constructors
    public Card() {

    }

    public Card(int id, Date createdOn, Date updatedOn, CardStatus cardStatus) {
        this.id = id;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.cardStatus = cardStatus;
    }

    //getters and setters

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getCreatedOn() { return createdOn; }
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; }

    public Date getUpdatedOn() { return updatedOn; }
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; }

    public CardStatus getCardStatus() { return cardStatus; }
    public void setCardStatus(CardStatus cardStatus) { this.cardStatus = cardStatus; }
}
