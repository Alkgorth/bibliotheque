package cda.bibliotheque.model;

public class Genre {
    private int id;
    private String label;

    public Genre() {
    }

    public Genre(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Genre(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
