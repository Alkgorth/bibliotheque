package cda.bibliotheque.controller.Genre;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.GenreDAO;
import cda.bibliotheque.model.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateGenreController {
    
    @FXML
    private TextField inputLabel;

    private final GenreDAO genreDAO = new GenreDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Genre genre = new Genre(inputLabel.getText());
        genreDAO.addGenre(genre);
        App.setRoot("genres/genre");
    }

    @FXML
    void switchToGenre(ActionEvent event) throws IOException {
        App.setRoot("genres/genre");
    }

}
