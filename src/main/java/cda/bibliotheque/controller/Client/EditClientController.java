package cda.bibliotheque.controller.Client;

import cda.bibliotheque.dao.ClientDAO;
import cda.bibliotheque.model.Client;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditClientController {
    private final ObjectProperty<Client> client = new SimpleObjectProperty<>();
    private ClientDAO clientDAO = new ClientDAO();

    private ClientController parentController;

    @FXML
    private TextField inputFirstName;
    
    @FXML
    private TextField inputLastName;

    @FXML
    private TextField inputEmail;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Client clientToUpdate = client.get();
        clientToUpdate.setFirstname(inputFirstName.getText());
        clientToUpdate.setLastname(inputLastName.getText());
        clientToUpdate.setEmail(inputEmail.getText());
        clientDAO.updateClient(clientToUpdate);
        if (parentController != null) {
            parentController.refreshClients();
        }
        closeWindow();
    }

    @FXML
    public void initialize(){
        client.addListener((obs, oldClient, newClient) -> {
            if (newClient != null) {
                inputFirstName.setText(newClient.getFirstname());
                inputLastName.setText(newClient.getLastname());
                inputEmail.setText(newClient.getEmail());
            }
        });
    }

    public EditClientController(){

    }

    public void setClient(Client client) {
        this.client.set(client);
    }

    public void setParentController(ClientController parentController) {
        this.parentController = parentController;
    }

    private void closeWindow() {
        Stage stage = (Stage) inputFirstName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void switchToClients() throws IOException {
        closeWindow();
    }
}
