package com.ncit.minor.futsalbookingapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date bookDate;

    @DateTimeFormat(pattern = "HH:mm")
    private Date bookTime;

    private String fakeDate;
    private String fakeTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Futsal futsal;


    public Booking() {
    }

    public long getId() {
        return this.id;
    }

    public Date getBookDate() {
        return this.bookDate;
    }

    public Date getBookTime() {
        return this.bookTime;
    }

    public User getUser() {
        return this.user;
    }

    public Futsal getFutsal() {
        return this.futsal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFutsal(Futsal futsal) {
        this.futsal = futsal;
    }

    public String getFakeDate() {
        return this.fakeDate;
    }

    public String getFakeTime() {
        return this.fakeTime;
    }

    public void setFakeDate(String fakeDate) {
        this.fakeDate = fakeDate;
    }

    public void setFakeTime(String fakeTime) {
        this.fakeTime = fakeTime;
    }
}
