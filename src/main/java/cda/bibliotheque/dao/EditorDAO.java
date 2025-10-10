package cda.bibliotheque.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Editor;

public class EditorDAO {

    private Connection connection;

    private final DistributeDAO distributeDAO = new DistributeDAO();

    public EditorDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Editor> getAllEditors(){
        List<Editor> editors = new ArrayList<>();
        String sql = "SELECT id, label, create_at  FROM editor";
        try (Statement stmt = connection.createStatement(); ResultSet rSet = stmt.executeQuery(sql)){
            while (rSet.next()) {
                editors.add(new Editor(
                    rSet.getInt("id"),
                    rSet.getString("label"),
                    rSet.getDate("create_at").toLocalDate()
                ));
            }            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération dans getAllEditors -> " + e.getMessage());
        }
        return editors;
    }

    public void addEditor(Editor editor){
        String sql = "INSERT INTO editor (label, created_at) VALUES (?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, editor.getLabel());
            pstmt.setDate(2, editor.getCreated_at_Date());
            pstmt.executeUpdate();
            System.out.println("Ajout de l'éditeur effectué");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout dans addEditor -> " + e.getMessage());
        }
    }

    public void updateEditor(Editor editor){
        String sql = "UPDATE editor SET label = ?, create_at = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, editor.getLabel());
            pstmt.setDate(2, editor.getCreated_at_Date());
            pstmt.setInt(3, editor.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Mise à jour de l'éditeur effectué");
            } else {
                System.out.println("L'éditeur n'existe pas.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour dans updateEditor -> " + e.getMessage());
        }
    }

    public void deleteEditor(int id){
        String sql = "DELETE FROM editor WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Suppression de l'éditeur effectuée");
            } else {
                System.out.println("L'éditeur n'existe pas.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression dans deleteEditor -> " + e.getMessage());
        }
    }
    
}
