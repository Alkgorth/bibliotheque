package cda.bibliotheque.dao;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import cda.bibliotheque.model.Book;

public class BookDAO {
    private Connection connection;
    
    public BookDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, title, release_date, isAvailable FROM books";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getDate("release_date").toLocalDate(),
                    rs.getBoolean("isAvailable")
                ));
            }
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des livres : " + e.getMessage());
        }
        return books;
    }

    public void addBook(Book book){
        String sql = "INSERT INTO books (title, release_date, isAvailable) VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, book.getTitle());
            pstmt.setDate(2, book.getRelease_date_Date());
            pstmt.setBoolean(3, book.isAvailable());
            pstmt.executeUpdate();
            System.out.println("Ajout du livre effectué");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout dans addBook -> " + e.getMessage());
        }
    }

    public void updateBook(Book book){
        String sql = "UPDATE books SET title = ?, release_date = ?, isAvailable = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, book.getTitle());
            pstmt.setDate(2, book.getRelease_date_Date());
            pstmt.setBoolean(3, book.isAvailable());
            pstmt.setInt(4, book.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Mise à jour du livre effectuée");
            } else {
                System.out.println("Le livre n'existe pas.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour dans updateBook -> " + e.getMessage());
        }
    }

    public void deleteBook(int id){
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Suppression du livre effectuée");
            } else {
                System.out.println("Le livre n'existe pas.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression dans deleteBook -> " + e.getMessage());
        }
    }
}
