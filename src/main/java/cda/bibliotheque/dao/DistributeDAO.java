package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Book;

public class DistributeDAO {
    private Connection connection;

    public DistributeDAO(){
        connection = DatabaseConnection.getConnection();
    }

    public List<Book> getBookByEditor(int id){
        String sql = "SELECT books.id, title, release_date, isAvailable FROM books INNER JOIN distribute ON books.id = book_id WHERE book.id = ?;";
        List<Book> books = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet rSet = preparedStatement.executeQuery();
            while (rSet.next()) {
                books.add(new Book(
                    rSet.getInt("id"),
                    rSet.getString("title"),
                    rSet.getDate("release_date").toLocalDate(),
                    rSet.getBoolean("isAvailable")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return books;
    }
}
