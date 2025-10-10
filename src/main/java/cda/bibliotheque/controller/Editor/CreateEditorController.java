package cda.bibliotheque.controller.Editor;

import java.io.IOException;

import cda.bibliotheque.dao.EditorDAO;
import cda.bibliotheque.model.Editor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEditorController {
    
    private final EditorDAO editorDAO = new EditorDAO();
    private EditorController parentController;

    @FXML
    private TextField inputLabel;

    @FXML
    private DatePicker inputCreatedAt;

    @FXML
    public void initialize() {
        // Initialisation des composants si nécessaire
    }

    @FXML
    void submit(ActionEvent event) throws IOException {
        Editor editor = new Editor();
        editor.setLabel(inputLabel.getText());
        editor.setCreated_at(inputCreatedAt.getValue());
        editorDAO.addEditor(editor);
        if (parentController != null) {
            parentController.refreshEditors();
        }
        closeWindow();
    }

    @FXML
    private void switchToEditors() throws IOException {
        closeWindow();
    }

    public void setParentController(EditorController parentController) {
        this.parentController = parentController;
    }

    private void closeWindow() {
        Stage stage = (Stage) inputLabel.getScene().getWindow();
        stage.close();
    }
}
