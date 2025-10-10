package cda.bibliotheque.controller.Genre;

import cda.bibliotheque.dao.GenreDAO;
import cda.bibliotheque.model.Genre;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditGenreController {
    private final ObjectProperty<Genre> genre = new SimpleObjectProperty<>();
    private GenreDAO genreDAO = new GenreDAO();

    private GenreController parentController;

    @FXML
    private TextField inputLabel;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Genre genreToUpdate = genre.get();
        genreToUpdate.setLabel(inputLabel.getText());
        genreDAO.updateGenre(genreToUpdate);
        if (parentController != null) {
            parentController.refreshGenres();
        }
        closeWindow();
    }

    @FXML
    public void initialize(){
        genre.addListener((obs, oldGenre, newGenre) -> {
            if (newGenre != null) {
                inputLabel.setText(newGenre.getLabel());
            }
        });
    }

    public EditGenreController(){

    }

    public void setGenre(Genre genre) {
        this.genre.set(genre);
    }
    
    public void setParentController(GenreController parentController) {
        this.parentController = parentController;
    }
    
    private void closeWindow() {
        Stage stage = (Stage) inputLabel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void switchToGenres() throws IOException {
        closeWindow();
    }
}