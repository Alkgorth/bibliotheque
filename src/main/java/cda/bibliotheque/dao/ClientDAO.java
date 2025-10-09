package cda.bibliotheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cda.bibliotheque.model.Client;

public class ClientDAO {
    private Connection connection;

    public ClientDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT id, lastname, firstname, emailFROM client";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                clients.add(new Client(
                    rs.getInt("id"),
                    rs.getString("lastname"),
                    rs.getString("firstname"),
                    rs.getString("email")
                ));
            }            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération dans getAllClients -> " + e.getMessage());
        }
        return clients;
    }

    public void addClient(Client client) {
        String sql = "INSERT INTO client (lastname, firstname, email) VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, client.getLastname());
            pstmt.setString(2, client.getFirstname());
            pstmt.setString(3, client.getEmail());
            pstmt.executeUpdate();
            System.out.println("Ajout du client effectué");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout dans addClient -> " + e.getMessage());
        }
    }

    public void updateClient(Client client) {
        String sql = "UPDATE client SET lastname = ?, firstname = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, client.getLastname());
            pstmt.setString(2, client.getFirstname());
            pstmt.setString(3, client.getEmail());
            pstmt.setInt(4, client.getId());
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Mise à jour du client effectué");
            } else {
                System.out.println("Aucun client trouvé avec l'ID spécifié");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour dans updateClient -> " + e.getMessage());
        }
    }

    public void deleteClient(int clientId) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1, clientId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Suppression du client effectuée");
            } else {
                System.out.println("Aucun client trouvé avec l'ID spécifié");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression dans deleteClient -> " + e.getMessage());
        }
    }
}
