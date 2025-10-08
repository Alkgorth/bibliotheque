package cda.bibliotheque.controller.Book;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateBookController {
    
    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    private ChoiceBox<String> inputAvailability;

    private final BookDAO bookDAO = new BookDAO();

    @FXML
    public void initialize() {
        inputAvailability.getItems().addAll("Oui", "Non");
        inputAvailability.setValue("Oui");
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book book = new Book();
        book.setRelease_date(inputReleaseDate.getValue());
        book.setTitle(inputTitle.getText());
        book.setAvailable("Oui".equals(inputAvailability.getValue()));

        bookDAO.addBook(book);
        App.setRoot("books/book");
    }
    
    @FXML
    private void switchToBooks() throws IOException {
        App.setRoot("books/book");
    }
}
