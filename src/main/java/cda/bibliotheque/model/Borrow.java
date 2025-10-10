package cda.bibliotheque.model;

import java.sql.Date;
import java.time.LocalDate;

public class Borrow {
    
    private Client client;
    private Book book;
    private LocalDate endDate;
    private LocalDate startDate;
    private boolean isDone;

    public Borrow() {
    }

    public Borrow(Client client, Book book, LocalDate endDate, LocalDate startDate, boolean isDone) {
        this.client = client;
        this.book = book;
        this.endDate = endDate;
        this.startDate = startDate;
        this.isDone = isDone;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate_DATE() {
        return Date.valueOf(endDate);
    }
    public void setEndDate_DATE(Date endDate) {
        this.endDate = endDate.toLocalDate();
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate_DATE() {
        return Date.valueOf(startDate);
    }
    public void setStartDate_DATE(Date startDate) {
        this.startDate = startDate.toLocalDate();
    }

    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
