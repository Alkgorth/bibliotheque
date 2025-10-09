package cda.bibliotheque.model;

import java.time.LocalDate;
import java.sql.Date;

public class Editor {
    private int id;
    private String label;
    private LocalDate created_at;

    public Editor() {
    }

    public Editor(int id, String label, LocalDate created_at) {
        this.id = id;
        this.label = label;
        this.created_at = created_at;
    }
    
    public Editor(int id, String label, Date created_at) {
        this.id = id;
        this.label = label;
        this.created_at = created_at.toLocalDate();
    }

    public int getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }
    public LocalDate getCreated_at() {
        return created_at;
    }
    public Date getCreated_at_Date() {
        return Date.valueOf(created_at);
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at.toLocalDate();
    }
}
