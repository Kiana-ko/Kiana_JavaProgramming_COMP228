package exercise1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class PlayerGameForm {

    public static VBox createPlayerGameForm() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label lblPlayer = new Label("Player:");
        ComboBox<String> cmbPlayer = new ComboBox<>();
        Label lblGame = new Label("Game:");
        ComboBox<String> cmbGame = new ComboBox<>();
        Label lblScore = new Label("Score:");
        TextField txtScore = new TextField();
        Label lblDate = new Label("Date:");
        DatePicker datePicker = new DatePicker(LocalDate.now());

        Button btnSubmit = new Button("Submit");
        Button btnRefreshPlayers = new Button("Refresh Players");
        Button btnRefreshGames = new Button("Refresh Games");

        gridPane.add(lblPlayer, 0, 0);
        gridPane.add(cmbPlayer, 1, 0);
        gridPane.add(btnRefreshPlayers, 2, 0);
        gridPane.add(lblGame, 0, 1);
        gridPane.add(cmbGame, 1, 1);
        gridPane.add(btnRefreshGames, 2, 1);
        gridPane.add(lblScore, 0, 2);
        gridPane.add(txtScore, 1, 2);
        gridPane.add(lblDate, 0, 3);
        gridPane.add(datePicker, 1, 3);
        gridPane.add(btnSubmit, 1, 4);

        vbox.getChildren().addAll(gridPane);

        // Load initial data
        loadPlayers(cmbPlayer);
        loadGames(cmbGame);

        // Button to refresh players list
        btnRefreshPlayers.setOnAction(e -> loadPlayers(cmbPlayer));

        // Button to refresh games list
        btnRefreshGames.setOnAction(e -> loadGames(cmbGame));

        // Submit button action
        btnSubmit.setOnAction(e -> {
            String selectedPlayer = cmbPlayer.getSelectionModel().getSelectedItem();
            String selectedGame = cmbGame.getSelectionModel().getSelectedItem();
            String scoreText = txtScore.getText();
            LocalDate selectedDate = datePicker.getValue();

            if (selectedPlayer == null || selectedGame == null || scoreText.isEmpty() || selectedDate == null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required!");
                return;
            }

            try {
                int playerId = Integer.parseInt(selectedPlayer.split(":")[0]);
                int gameId = Integer.parseInt(selectedGame.split(":")[0]);
                int score = Integer.parseInt(scoreText);

                insertPlayerGame(playerId, gameId, Date.valueOf(selectedDate), score);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Player game record added successfully!");
                cmbPlayer.getSelectionModel().clearSelection();
                cmbGame.getSelectionModel().clearSelection();
                txtScore.clear();
                datePicker.setValue(LocalDate.now());
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Could not add player game record: " + ex.getMessage());
            }
        });

        return vbox;
    }

    private static void loadPlayers(ComboBox<String> comboBox) {
        try (Connection connection = ConnectionDatabase.connection()) {
            String sql = "SELECT player_id, first_name, last_name FROM Player";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ObservableList<String> players = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                players.add(playerId + ": " + firstName + " " + lastName);
            }
            comboBox.setItems(players);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not load players: " + e.getMessage());
        }
    }

    private static void loadGames(ComboBox<String> comboBox) {
        try (Connection connection = ConnectionDatabase.connection()) {
            String sql = "SELECT game_id, game_title FROM Game";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ObservableList<String> games = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int gameId = resultSet.getInt("game_id");
                String gameTitle = resultSet.getString("game_title");
                games.add(gameId + ": " + gameTitle);
            }
            comboBox.setItems(games);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not load games: " + e.getMessage());
        }
    }

    private static void insertPlayerGame(int playerId, int gameId, Date playingDate, int score) throws Exception {
        try (Connection connection = ConnectionDatabase.connection()) {
            String sql = "INSERT INTO PlayerAndGame (player_game_id, game_id, player_id, playing_date, score) VALUES (seq_player_game.NEXTVAL, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gameId);
            statement.setInt(2, playerId);
            statement.setDate(3, playingDate);
            statement.setInt(4, score);
            statement.executeUpdate();
        }
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
