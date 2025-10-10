package cda.bibliotheque.controller.Book;

import java.io.IOException;
import java.util.List;

import cda.bibliotheque.dao.AuthorDAO;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Author;
import cda.bibliotheque.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateBookController {
    
    private final BookDAO bookDAO = new BookDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();
    private BookController parentController;
    
    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    private ChoiceBox<String> inputAvailability;
    
    @FXML
    private ListView<Author> inputAuthors;

    @FXML
    public void initialize() {
        inputAvailability.getItems().addAll("Oui", "Non");
        inputAvailability.setValue("Oui");
        List<Author> authors = authorDAO.getAllAuthors();
        inputAuthors.getItems().setAll(authors);
        inputAuthors.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book book = new Book();//Ajouter les paramètres au constructeur pour la création de l'objet Book
        book.setRelease_date(inputReleaseDate.getValue());
        book.setTitle(inputTitle.getText());
        book.setAvailable("Oui".equals(inputAvailability.getValue()));
        bookDAO.addBook(book);
        if (parentController != null) {
            parentController.refreshBooks();
        }
        closeWindow();
    }
    
    @FXML
    private void switchToBooks() throws IOException {
        closeWindow();
    }

    public void setParentController(BookController parentController) {
        this.parentController = parentController;
    }

    private void closeWindow() {
        Stage stage = (Stage) inputTitle.getScene().getWindow();
        stage.close();
    }
}
