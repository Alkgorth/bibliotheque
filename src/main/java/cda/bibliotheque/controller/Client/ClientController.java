package cda.bibliotheque.controller.Client;

import java.io.IOException;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.ClientDAO;
import cda.bibliotheque.model.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class ClientController {

    private final ObservableList<Client> clientList = FXCollections.observableArrayList();
    private ClientDAO clientDAO = new ClientDAO();

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> colFirstName;
    
    @FXML
    private TableColumn<Client, String> colLastName;

    @FXML
    private TableColumn<Client, String> colEmail;
    
    @FXML
    private TableColumn<Client, Void> colActions;

    @FXML
    public void initialize(){
        colFirstName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstname()));
        colLastName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLastname()));
        colEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Client clientToEdit = clientTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("clients/edit-client.fxml"));
                        Parent root = loader.load();
                        EditClientController controller = loader.getController();
                        controller.setClient(clientToEdit);
                        controller.setParentController(ClientController.this);
                        Stage stage = new Stage();
                        stage.setTitle("Modifier un client");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Client clientToDelete = clientTable.getItems().get(index);
                    clientDAO.deleteClient(clientToDelete.getId());
                    loadClients();
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
        loadClients();
    }

    private void loadClients() {
        clientList.clear();
        clientList.setAll(clientDAO.getAllClients());
        clientTable.setItems(clientList);
    }

    public void refreshClients() {
        loadClients();
    }

    @FXML
    private void switchToCreateClient() throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("clients/create-client.fxml"));
            Parent root = loader.load();
            CreateClientController controller = loader.getController();
            controller.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Créer un nouveau client");
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
