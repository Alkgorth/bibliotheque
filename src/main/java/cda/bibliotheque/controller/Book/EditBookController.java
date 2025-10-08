package cda.bibliotheque.controller.Book;

import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Book;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditBookController {
    private final ObjectProperty<Book> book = new SimpleObjectProperty<>();
    private BookDAO bookDAO = new BookDAO();

    private BookController parentController;

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    private ChoiceBox<String> inputAvailability;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book bookToUpdate = book.get();
        bookToUpdate.setRelease_date(inputReleaseDate.getValue());
        bookToUpdate.setTitle(inputTitle.getText());
        boolean isAvailable = "Oui".equals(inputAvailability.getValue());
        bookToUpdate.setAvailable(isAvailable);
        bookDAO.updateBook(bookToUpdate);
        if (parentController != null) {
            parentController.refreshBooks();
        }
        closeWindow();
    }

    @FXML
    public void initialize(){
        if (inputAvailability != null) {
            inputAvailability.getItems().addAll("Oui", "Non");
        }
        book.addListener((obs, oldBook, newBook) -> {
            if (newBook != null) {
                inputReleaseDate.setValue(newBook.getRelease_date());
                inputTitle.setText(newBook.getTitle());
                if (inputAvailability != null) {
                    inputAvailability.setValue(newBook.isAvailable() ? "Oui" : "Non");
                }
            }
        });
    }

    public EditBookController(){

    }

    public void setBook(Book book) {
        this.book.set(book);
    }
    
    public void setParentController(BookController parentController) {
        this.parentController = parentController;
    }
    
    private void closeWindow() {
        Stage stage = (Stage) inputTitle.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void switchToBooks() throws IOException {
        closeWindow();
    }
}
