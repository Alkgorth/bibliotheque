package cda.bibliotheque.model;

import java.sql.Date;
import java.time.LocalDate;

public class Author {
    private int id;
    private String lastname;
    private String firstname;
    private LocalDate born_at;

    public Author() {
        //permet de créer un objet auteur sans rien lui passer en paramètre (sans rien dedans)
        //de plus la création de plusieurs constructeurs est possible, en leur passant des paramètres différents
    }

    public Author(int id, String lastname, String firstname, LocalDate born_at) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.born_at = born_at;
    }

    public Author(int id, String lastname, String firstname, Date born_at) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.born_at = born_at.toLocalDate();
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public LocalDate getBorn_at() {
        return born_at;
    }

    public Date getBorn_at_Date() {
        return Date.valueOf(born_at);
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setBorn_at(LocalDate born_at) {
        this.born_at = born_at;
    }

    public void setBorn_at(Date born_at) {
        this.born_at = born_at.toLocalDate();
    }    
}
