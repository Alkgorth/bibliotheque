package cda.bibliotheque.controller;

import java.io.IOException;
import cda.bibliotheque.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToAuthors() throws IOException {
        App.setRoot("authors/authors");
    }
    
    @FXML
    private void switchToBooks() throws IOException {
        App.setRoot("books/book");
    }

    @FXML
    private void switchToEditors() throws IOException {
        App.setRoot("editors/editor");
    }

    @FXML
    private void switchToClients() throws IOException {
        App.setRoot("clients/client");
    }
}
