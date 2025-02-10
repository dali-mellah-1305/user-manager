package com.example.demo1;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class login_con {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    // Méthode appelée lorsque l'utilisateur clique sur le bouton "Se connecter"
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Vérification des identifiants (simplifiée pour l'exemple)
        if ("admin".equals(username) && "admin123".equals(password)) {
            // Ouvrir la page de gestion des utilisateurs
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(loader.load(), 800, 400);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setTitle("Formulaire  Add Users");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
            alert.showAndWait();
        }
    }
}