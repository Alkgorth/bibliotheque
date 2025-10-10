package cda.bibliotheque.dao;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import cda.bibliotheque.model.Book;
import cda.bibliotheque.model.Genre;
import cda.bibliotheque.model.Author;

public class BookDAO {
    private Connection connection;
    private final WriteDAO writeDAO = new WriteDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();
    private final GenreDAO genreDAO = new GenreDAO();
    
    public BookDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, title, release_date, isAvailable FROM books";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                List<Author> authors = authorDAO.getAuthorsByBook(rs.getInt("id"));
                List<Genre> genres = genreDAO.getGenresByBook(rs.getInt("id"));
                Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getDate("release_date").toLocalDate(),
                    rs.getBoolean("isAvailable")
                );
                book.setAuthors(getAuthorsByBookId(book.getId()));
                books.add(book);
            }
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des livres : " + e.getMessage());
        }
        return books;
    }

    public Book getLastBookInserted(){
        String sql = "SELECT id, title, release_date, isAvailable FROM books ORDER BY id DESC LIMIT 1";
        Book book = new Book();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            if (rs.next()) {
                book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getDate("release_date").toLocalDate(),
                    rs.getBoolean("isAvailable")
                );
                book.setAuthors(getAuthorsByBookId(book.getId()));
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du dernier livre inséré : " + e.getMessage());
        }
        return book;
    }

    private List<Author> getAuthorsByBookId(int bookId) {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT a.id, a.lastname, a.firstname, a.born_at " +
                    "FROM author a " +
                    "INNER JOIN book_author ba ON a.id = ba.author_id " +
                    "WHERE ba.book_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                authors.add(new Author(
                    rs.getInt("id"),
                    rs.getString("lastname"),
                    rs.getString("firstname"),
                    rs.getDate("born_at")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des auteurs pour le livre " + bookId + " : " + e.getMessage());
        }
        
        return authors;
    }

    public void addBook(Book book){
        String sql = "INSERT INTO books (title, release_date, isAvailable) VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, book.getTitle());
            pstmt.setDate(2, book.getRelease_date_Date());
            pstmt.setBoolean(3, book.isAvailable());
            pstmt.executeUpdate();
            Book lastBook = getLastBookInserted();
            for (Author author : book.getAuthors()) {
                writeDAO.insert(author.getId(), lastBook.getId());
                
            }
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
            writeDAO.deleteByBookId(book.getId());
            for (Author author : book.getAuthors()) {
                writeDAO.insert(author.getId(), book.getId());
            }
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
