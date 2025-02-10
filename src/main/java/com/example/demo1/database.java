package com.example.demo1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class database {
    private static final String URL = "jdbc:mysql://localhost:3306/esprit"; // Remplacez localhost par votre hôte si nécessaire
    private static final String USER = "root"; // Votre nom d'utilisateur MySQL
    private static final String PASSWORD = ""; // Votre mot de passe MySQL (laissez vide si non défini)

    public static Connection getConnection() {
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se connecter à la base de données
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
