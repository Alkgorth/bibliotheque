package cda.bibliotheque.controller.Book;

import cda.bibliotheque.App;
import cda.bibliotheque.model.Book;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cda.bibliotheque.dao.BookDAO;

import java.io.IOException;
import java.time.LocalDate;

public class BookController {
    
    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> colTitle;

    @FXML
    private TableColumn<Book, LocalDate> colReleaseDate;

    @FXML
    private TableColumn<Book, String> colAuthors;

    @FXML
    private TableColumn<Book, Boolean> colIsAvailable;
    
    @FXML
    private TableColumn<Book, Void> colActions;

    private final ObservableList<Book> booksList = FXCollections.observableArrayList();
    private final BookDAO bookDAO = new BookDAO();

    @FXML
    public void initialize(){
        colTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        colIsAvailable.setCellFactory(column -> new TableCell<Book, Boolean>(){
            private final CheckBox checkBox = new CheckBox();
            {
                checkBox.selectedProperty().addListener((obs, oldValue, newValue) -> {
                    Book book = getTableRow().getItem();
                    if (book != null) {
                        book.setAvailable(newValue);
                        bookDAO.updateBook(book);
                        System.out.println("Disponibilité du livre '" + book.getTitle() + "' mise à jour : " + newValue);
                    }
                });
            }
            
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(item);
                    setGraphic(checkBox);
                }
            }
        });
        colIsAvailable.setCellValueFactory(cell -> {
            Book book = cell.getValue();
            return new SimpleObjectProperty<>(book.isAvailable());
        });
        
        colReleaseDate.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getRelease_date()));
        colAuthors.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().toStringAuthors()));
        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final VBox box = new VBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToEdit = booksTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("books/edit-book.fxml"));
                        Parent root = loader.load();
                        EditBookController controller = loader.getController();
                        controller.setBook(bookToEdit);
                        controller.setParentController(BookController.this);
                        Stage stage = new Stage();
                        stage.setTitle("Modifier un livre");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Book bookToDelete = booksTable.getItems().get(index);
                    bookDAO.deleteBook(bookToDelete.getId());
                    loadBooks();
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
        loadBooks();
    }

    private void loadBooks(){
        booksList.clear();
        booksList.setAll(bookDAO.getAllBooks());
        booksTable.setItems(booksList);
    }

    /*
     * private void loadBooks() {
     *  List<Book> books = bookDAO.getAllBook();
     *  this.booksList.setAll(books);
     * }
     */

    public void refreshBooks() {
        loadBooks();
    }

    @FXML
    private void switchToCreateBook() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("books/create-book.fxml"));
            Parent root = loader.load();
            CreateBookController controller = loader.getController();
            controller.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Créer un nouveau livre");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
