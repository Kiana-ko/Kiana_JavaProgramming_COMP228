package exercise1;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GameForm {

    public static GridPane createGameForm() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label lblTitle = new Label("Game Title:");
        TextField txtTitle = new TextField();
        Button btnSubmit = new Button("Add Game");

        gridPane.add(lblTitle, 0, 0);
        gridPane.add(txtTitle, 1, 0);
        gridPane.add(btnSubmit, 1, 1);

        btnSubmit.setOnAction(e -> {
            String gameTitle = txtTitle.getText();
            if (gameTitle.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Game title cannot be empty!");
                return;
            }

            try (Connection connection = ConnectionDatabase.connection()) {
                String sql = "INSERT INTO Game (game_id, game_title) VALUES (seq_game.NEXTVAL, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, gameTitle);
                statement.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Game added successfully!");
                txtTitle.clear();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Could not add game: " + ex.getMessage());
            }
        });

        return gridPane;
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
