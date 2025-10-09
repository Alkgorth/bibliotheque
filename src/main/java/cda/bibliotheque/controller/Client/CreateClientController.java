package cda.bibliotheque.controller.Client;

import java.io.IOException;

import cda.bibliotheque.dao.ClientDAO;
import cda.bibliotheque.model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateClientController {
    
    private ClientDAO clientDAO = new ClientDAO();
    private ClientController parentController;

    @FXML
    private TextField inputFirstName;
    
    @FXML
    private TextField inputLastName;

    @FXML
    private TextField inputEmail;

    @FXML
    public void submit() {
        Client client = new Client();
        client.setFirstname(inputFirstName.getText());
        client.setLastname(inputLastName.getText());
        client.setEmail(inputEmail.getText());
        clientDAO.addClient(client);
        if (parentController != null){
            parentController.refreshClients();
        }        
        closeWindow();
    }

    @FXML
    private void switchToClients() throws IOException {
        closeWindow();
    }

    public void setParentController(ClientController parentController){
        this.parentController = parentController;
    }

    private void closeWindow() {
        Stage stage = (Stage) inputEmail.getScene().getWindow();
        stage.close();
    }
}
