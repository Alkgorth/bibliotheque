package cda.bibliotheque.model;

public class Client {
    private int id;
    private String lastname;
    private String firstname;
    private String email;

    public Client() {
    }

    public Client(int id, String lastname, String firstname, String email) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
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
    public String getEmail() {
        return email;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
