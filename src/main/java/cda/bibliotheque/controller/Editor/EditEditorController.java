package cda.bibliotheque.controller.Editor;

import cda.bibliotheque.dao.EditorDAO;
import cda.bibliotheque.model.Editor;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditEditorController {
    private final ObjectProperty<Editor> editor = new SimpleObjectProperty<>();
    private EditorDAO editorDAO = new EditorDAO();

    private EditorController parentController;

    @FXML
    private DatePicker inputCreatedAt;

    @FXML
    private TextField inputLabel;

    @FXML
    void submit(ActionEvent event) throws IOException {
        Editor editorToUpdate = editor.get();
        editorToUpdate.setCreated_at(inputCreatedAt.getValue());
        editorToUpdate.setLabel(inputLabel.getText());
        editorDAO.updateEditor(editorToUpdate);
        if (parentController != null) {
            parentController.refreshEditors();
        }
        closeWindow();
    }

    @FXML
    public void initialize(){
        editor.addListener((obs, oldEditor, newEditor) -> {
            if (newEditor != null) {
                inputCreatedAt.setValue(newEditor.getCreated_at());
                inputLabel.setText(newEditor.getLabel());
            }
        });
    }

    public EditEditorController(){

    }

    public void setEditor(Editor editor) {
        this.editor.set(editor);
    }
    
    public void setParentController(EditorController parentController) {
        this.parentController = parentController;
    }
    
    private void closeWindow() {
        Stage stage = (Stage) inputLabel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void switchToEditors() throws IOException {
        closeWindow();
    }
}
