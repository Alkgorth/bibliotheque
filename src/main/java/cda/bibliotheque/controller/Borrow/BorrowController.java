package cda.bibliotheque.controller.Borrow;

import cda.bibliotheque.dao.BorrowDAO;
import cda.bibliotheque.model.Borrow;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.LocalDate;


public class BorrowController {

    @FXML
    private TableView<Borrow> booksTable;

    @FXML
    private TableColumn<Borrow, Void> colActions;

    @FXML
    private TableColumn<Borrow, String> colBook;

    @FXML
    private TableColumn<Borrow, String> colClient;

    @FXML
    private TableColumn<Borrow, LocalDate> colIsEndDate;

    @FXML
    private TableColumn<Borrow, Boolean> colIsDone;

    @FXML
    private TableColumn<Borrow, LocalDate> colStartDate;

    private final BorrowDAO borrowDAO = new BorrowDAO();
    private ObservableList<Borrow> borrows = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        colClient.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getClient().getFirstname() + " " + cell.getValue().getClient().getLastname()));
        colBook.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBook().getTitle()));
        colIsEndDate.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getEndDate()));
        colStartDate.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getStartDate()));
        colIsDone.setCellValueFactory(cell -> new SimpleBooleanProperty(cell.getValue().isDone()));
    }

    @FXML
    void switchToCreateBorrow(ActionEvent event) {

    }

    @FXML
    void switchToPrimary(ActionEvent event) {

    }

}
