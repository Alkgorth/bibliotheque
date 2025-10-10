package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Book;
import cda.bibliotheque.model.Borrow;
import cda.bibliotheque.model.Client;

public class BorrowDAO {
    
    private Connection connection;

    public BorrowDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<?> getAllBorrows() {
        String sql = "SELECT book_id, client_id, end_date, start_date, isDone, title, release_date, isAvailable, firstname, lastname, email " +
                       " FROM borrow " +
                       " INNER JOIN books ON book_id = books.id" +
                       " INNER JOIN clients ON client_id = clients.id";
        List<Borrow> borrows = new ArrayList<>();
            try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Client client = new Client(
                        rs.getInt("client_id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email")
                    );
                    Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getDate("release_date").toLocalDate(),
                        rs.getBoolean("isAvailable")
                    );
                    borrows.add(new Borrow(
                        client,
                        book,
                        rs.getDate("end_date").toLocalDate(),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getBoolean("isDone")
                    ));
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la récupération des emprunts : " + e.getMessage());
            }
            return borrows;
    }

    public void addBorrows(Borrow borrow) {
        String sql ="INSERT INTO borrow(book_id, client_id, end_date, start_date, isDone) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);){
            pstmt.setInt(1, borrow.getBook().getId());
            pstmt.setInt(2, borrow.getClient().getId());
            pstmt.setDate(3, borrow.getEndDate_DATE());
            pstmt.setDate(4, borrow.getStartDate_DATE());
            pstmt.setBoolean(5, borrow.isDone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout d'un emprunt : " + e.getMessage());
        }
    }

    public void updateBorrow(Borrow borrow) {
        String sql = "UPDATE borrow SET book_id = ?, client_id = ?, end_date = ?, start_date = ?, isDone = ? WHERE book_id = ? AND client_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, borrow.getBook().getId());
            pstmt.setInt(2, borrow.getClient().getId());
            pstmt.setDate(3, borrow.getEndDate_DATE());
            pstmt.setDate(4, borrow.getStartDate_DATE());
            pstmt.setBoolean(5, borrow.isDone());
            pstmt.setInt(6, borrow.getBook().getId());
            pstmt.setInt(7, borrow.getClient().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour d'un emprunt : " + e.getMessage());
        }
    }

    public void deleteBorrow(Borrow borrow) {
        String sql = "DELETE FROM borrow WHERE book_id = ? AND client_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, borrow.getBook().getId());
            pstmt.setInt(2, borrow.getClient().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression d'un emprunt : " + e.getMessage());
        }
    }

}
