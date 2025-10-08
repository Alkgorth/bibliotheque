package cda.bibliotheque.controller.Book;

import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Book;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditBookController {
    private final ObjectProperty<Book> book = new SimpleObjectProperty<>();
    private BookDAO bookDAO = new BookDAO();

    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    private TextField actionStatus;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book newBook = book.get();
        newBook.setRelease_date(inputReleaseDate.getValue());
        newBook.setTitle(inputTitle.getText());
        newBook.setAvailable(true);
        bookDAO.updateBook(newBook);
        actionStatus.setText("Livre modifié avec succès !");
    }
}
