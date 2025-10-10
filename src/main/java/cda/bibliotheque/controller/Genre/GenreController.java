package cda.bibliotheque.controller.Genre;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.GenreDAO;
import cda.bibliotheque.model.Genre;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GenreController {
    
    private final ObservableList<Genre> genresList = FXCollections.observableArrayList();
    private final GenreDAO genreDAO = new GenreDAO();

    @FXML
    private TableView<Genre> genresTable;

    @FXML
    private TableColumn<Genre, String> colLabelGenre;

    @FXML
    private TableColumn<Genre, Void> colActions;

    @FXML
    void switchToCreate(ActionEvent event) throws IOException {
        App.setRoot("genres/create-genre");
    }

    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("genres/primary-genre");
    }

    @FXML
    public void initialize(){
        colLabelGenre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLabel()));
        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Genre genreToEdit = genresTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("genres/edit-genre.fxml"));
                        Parent root = loader.load();
                        EditGenreController controller = loader.getController();
                        controller.setGenre(genreToEdit);
                        controller.setParentController(GenreController.this);
                        Stage stage = new Stage();
                        stage.setTitle("Modifier un genre");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        System.err.println("Erreur lors de la création du bouton edit -> " + e.getMessage());
                    }
                });
                
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Genre genreToDelete = genresTable.getItems().get(index);
                    genreDAO.deleteGenre(genreToDelete);
                    loadGenres();
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(box);
                }
            }
        });
        loadGenres();
    }

    private void loadGenres(){
        genresList.clear();
        genresList.setAll(genreDAO.getAllGenres());
        genresTable.setItems(genresList);
    }

    public void refreshGenres() {
        loadGenres();
    }

}
