package com.example.demo1;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.sql.ResultSet;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.GridPane;

public class HelloController extends Application {
    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;

    @FXML
    private void handleSubmit() {
        String id = idField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        System.out.println("ID: " + id + ", Nom: " + nom + ", Prénom: " + prenom);
        String sql = "INSERT INTO personne (id, nom,prenom) VALUES (?, ?,?)";

        try (Connection conn = database.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Remplir les paramètres de la requête

            statement.setString(1, id);
            statement.setString(2, nom);
            statement.setString(3, prenom);
            // Exécuter la requête d'insertion
            int rowsAffected = statement.executeUpdate();

            // rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return false;
    }
    @Override
    public void start(Stage stage) throws IOException {
        // Créer le TableView
        TableView<Personne> tableView = new TableView<>();

        // Créer les colonnes pour ID, Nom, Prénom
        TableColumn<Personne, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Personne, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Personne, String> colPrenom = new TableColumn<>("Prénom");
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // Ajouter les colonnes au TableView
        tableView.getColumns().add(colId);
        tableView.getColumns().add(colNom);
        tableView.getColumns().add(colPrenom);

        // Charger les données depuis la base de données
        String query = "SELECT * FROM personne";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                System.out.println("ID: " + id + ", Nom: " + nom + ", Prénom: " + prenom);
                // Ajouter les données dans le TableView
                tableView.getItems().add(new Personne(id, nom, prenom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Créer la scène avec un TableView direct
        stage.setScene(new Scene(tableView, 400, 400)); // Associer directement la scène au TableView
        stage.setTitle("Table des personnes");
        stage.show(); // Afficher la fenêtre avec le TableView
    }


    public void initialize() {
        // Ajouter les en-têtes

    }
    public static class Personne {
        private int id;
        private String nom;
        private String prenom;

        public Personne(int id, String nom, String prenom) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
        }

        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }
    }

    @FXML
    private void handleCancel() {
        idField.clear();
        nomField.clear();
        prenomField.clear();
    }


}