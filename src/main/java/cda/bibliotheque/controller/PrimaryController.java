package cda.bibliotheque.controller;

import java.io.IOException;
import cda.bibliotheque.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("authors/authors");
    }
    
    @FXML
    private void switchToBooks() throws IOException {
        App.setRoot("books/book");
    }
}
