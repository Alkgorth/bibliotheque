package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteDAO {
    private Connection connection;

    public WriteDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void insert(int author_id, int book_id){
        String sql = "INSERT INTO WRITES (book_id, author_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, book_id);
            pstmt.setInt(2, author_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion dans WRITES : " + e.getMessage());
        }
    }

    public void deleteByBookId(int book_id){
        String sql = "DELETE FROM WRITES WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, book_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression dans WRITES : " + e.getMessage());
        }
    }
}
