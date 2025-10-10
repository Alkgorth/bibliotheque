package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Genre;

public class GenreDAO {
    private Connection connection;

    public GenreDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Genre> getAllGenres() {
        String sql = "SELECT id, label FROM gender";
        List<Genre> genres = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                genres.add(new Genre(
                        rs.getInt("id"),
                        rs.getString("label")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des genres : " + e.getMessage());
        }
        return genres;
    }

    public void addGenre(Genre genre) {
        String sql = "INSERT INTO gender (label) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, genre.getLabel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout d'un genre : " + e.getMessage());
        }
    }

    public void updateGenre(Genre genre) {
        String sql = "UPDATE gender SET label = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, genre.getLabel());
            pstmt.setInt(2, genre.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour d'un genre : " + e.getMessage());
        }
    }

    public void deleteGenre(Genre genre) {
        String sql = "DELETE FROM gender WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, genre.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression d'un genre : " + e.getMessage());
        }
    }

    public List<Genre> getGenresByBook(int id) {
        String sql = "SELECT id, label FROM gender INNER JOIN have ON id = gender_id WHERE book_id = ?";
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                genres.add(new Genre(
                        rs.getInt("id"),
                        rs.getString("label")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return genres;
    }
}

