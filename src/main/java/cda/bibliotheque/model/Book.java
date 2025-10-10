package cda.bibliotheque.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Book {
    private int id;
    private String title;
    private LocalDate release_date;
    private boolean isAvailable;
    private List<Author> authors;

    public Book() {
        this.authors = new ArrayList<>();
    }

    public Book(int id, String title, LocalDate release_date, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.release_date = release_date;
        this.isAvailable = isAvailable;
        this.authors = new ArrayList<>();
    }

    public Book(int id, String title, Date release_date, boolean isAvailable, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.release_date = release_date.toLocalDate();
        this.isAvailable = isAvailable;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors != null ? authors : new ArrayList<>();
    }

    public Date getRelease_date_Date() {
        return Date.valueOf(release_date);
    }

    public String toStringAuthors(){
        if (authors == null || authors.isEmpty()) {
            return "Aucun auteur";
        }
        
        StringBuilder authorsString = new StringBuilder();
        for (int i = 0; i < authors.size(); i++) {
            if (i > 0) {
                authorsString.append(", ");
            }
            authorsString.append(authors.get(i).getFirstname())
                        .append(" ")
                        .append(authors.get(i).getLastname());
        }
        return authorsString.toString();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release_date=" + release_date +
                ", isAvailable=" + isAvailable +
                ", authors=" + toStringAuthors() +
                '}';
    }
}