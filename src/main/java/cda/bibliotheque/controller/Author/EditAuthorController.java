package cda.bibliotheque.controller.Author;

import java.io.IOException;
import cda.bibliotheque.App;
import cda.bibliotheque.model.Author;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import cda.bibliotheque.dao.AuthorDAO;

public class EditAuthorController {
    private final ObjectProperty<Author> author = new SimpleObjectProperty<>();
    private AuthorDAO authorDAO = new AuthorDAO();

    @FXML
    private DatePicker inputDateBornAt;

    @FXML
    private TextField inputFirstName;

    @FXML
    private TextField inputLastName;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Author newAuthor = author.get();
        newAuthor.setBorn_at(inputDateBornAt.getValue());
        newAuthor.setFirstname(inputFirstName.getText());
        newAuthor.setLastname(inputLastName.getText());
        authorDAO.updateAuthor(newAuthor);
        App.setRoot("authors/authors");
    }

    @FXML
    public void initialize(){
        author.addListener((obs, oldAuthor, newAuthor) -> {
            if (newAuthor != null) {
                inputDateBornAt.setValue(newAuthor.getBorn_at());
                inputFirstName.setText(newAuthor.getFirstname());
                inputLastName.setText(newAuthor.getLastname());
            }
        });
    }

    public EditAuthorController(){

    }

    public void setAuthor(Author author) {
        this.author.set(author);
    }
}
