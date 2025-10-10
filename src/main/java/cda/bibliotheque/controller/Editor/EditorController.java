package cda.bibliotheque.controller.Editor;

import java.io.IOException;
import java.time.LocalDate;

import cda.bibliotheque.App;
import cda.bibliotheque.dao.EditorDAO;
import cda.bibliotheque.model.Editor;
import javafx.beans.property.SimpleObjectProperty;
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

public class EditorController {
    
    private final ObservableList<Editor> editorsList = FXCollections.observableArrayList();
    private final EditorDAO editorDAO = new EditorDAO();

    @FXML
    private TableView<Editor> editorsTable;

    @FXML
    private TableColumn<Editor, String> colLabel;

    @FXML
    private TableColumn<Editor, LocalDate> colCreatedAt;
    
    @FXML
    private TableColumn<Editor, Void> colActions;

    @FXML
    public void initialize(){
        colLabel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLabel()));
        colCreatedAt.setCellValueFactory(cell -> new SimpleObjectProperty<LocalDate>(cell.getValue().getCreated_at()));
        colActions.setCellFactory(cell -> new TableCell<>(){
            private final Button buttonEdit = new Button("Modifier");
            private final Button buttonDelete = new Button("Supprimer");
            private final HBox box = new HBox(10, buttonEdit, buttonDelete);
            {
                buttonEdit.setOnAction(event -> {
                    int index = getIndex();
                    Editor editorToEdit = editorsTable.getItems().get(index);
                    try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("editors/edit-editor.fxml"));
                        Parent root = loader.load();
                        EditEditorController controller = loader.getController();
                        controller.setEditor(editorToEdit);
                        controller.setParentController(EditorController.this);
                        Stage stage = new Stage();
                        stage.setTitle("Modifier un éditeur");
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                buttonDelete.setOnAction(event -> {
                    int index = getIndex();
                    Editor editorToDelete = editorsTable.getItems().get(index);
                    editorDAO.deleteEditor(editorToDelete.getId());
                    loadEditors();
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
        loadEditors();
    }

    private void loadEditors(){
        editorsList.clear();
        editorsList.setAll(editorDAO.getAllEditors());
        editorsTable.setItems(editorsList);
    }

    public void refreshEditors() {
        loadEditors();
    }

    @FXML
    private void switchToCreateEditor() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editors/create-editor.fxml"));
            Parent root = loader.load();
            CreateEditorController controller = loader.getController();
            controller.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Créer un nouvel éditeur");
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
