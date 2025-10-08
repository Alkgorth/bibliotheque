package cda.bibliotheque.controller.Book;

import cda.bibliotheque.model.Book;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;

public class BookController {
    
    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> colTitle;

    @FXML
    private TableColumn<Book, LocalDate> colReleaseDate;

    @FXML
    private TableColumn<Book, Boolean> colIsAvailable;

    @FXML
    public void initialize(){
        // Initialisation des colonnes
        colTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        colIsAvailable.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isAvailable()));
        colReleaseDate.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getRelease_date()));
        colActions.setCellValueFactory(cell -> new TableCell<>(){
            // Actions (boutons modifier/supprimer)
        });
    }
}
