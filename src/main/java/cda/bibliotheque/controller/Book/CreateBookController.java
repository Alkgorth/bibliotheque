package cda.bibliotheque.controller.Book;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.BookDAO;
import cda.bibliotheque.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreateBookController {
    
    @FXML
    private DatePicker inputReleaseDate;

    @FXML
    private TextField inputTitle;

    @FXML
    private TextField actionStatus;

    private final BookDAO bookDAO = new BookDAO();

    @FXML
    void submit(ActionEvent event) throws IOException {
        Book book = new Book();
        book.setRelease_date(inputReleaseDate.getValue());
        book.setTitle(inputTitle.getText());
        book.setAvailable(true);
        bookDAO.addBook(book);
        actionStatus.setText("Livre ajouté avec succès !");
    }
}
