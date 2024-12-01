package exercise1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerReportForm {

    public static VBox createPlayerReportForm() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label lblSelectPlayer = new Label("Select Player:");
        ComboBox<String> cmbPlayer = new ComboBox<>();
        Button btnLoad = new Button("Load Report");
        Button btnRefresh = new Button("Refresh Players"); // Add Refresh Players button

        // Labels to display player details
        Label lblFirstName = new Label("First Name:");
        Label lblLastName = new Label("Last Name:");
        Label lblAddress = new Label("Address:");
        Label lblPostalCode = new Label("Postal Code:");
        Label lblProvince = new Label("Province:");
        Label lblPhoneNumber = new Label("Phone Number:");

        Label txtFirstName = new Label();
        Label txtLastName = new Label();
        Label txtAddress = new Label();
        Label txtPostalCode = new Label();
        Label txtProvince = new Label();
        Label txtPhoneNumber = new Label();

        // Add components to the grid
        gridPane.add(lblSelectPlayer, 0, 0);
        gridPane.add(cmbPlayer, 1, 0);
        gridPane.add(btnLoad, 2, 0);
        gridPane.add(btnRefresh, 3, 0); // Add Refresh button to the grid

        gridPane.add(lblFirstName, 0, 1);
        gridPane.add(txtFirstName, 1, 1);
        gridPane.add(lblLastName, 0, 2);
        gridPane.add(txtLastName, 1, 2);
        gridPane.add(lblAddress, 0, 3);
        gridPane.add(txtAddress, 1, 3);
        gridPane.add(lblPostalCode, 0, 4);
        gridPane.add(txtPostalCode, 1, 4);
        gridPane.add(lblProvince, 0, 5);
        gridPane.add(txtProvince, 1, 5);
        gridPane.add(lblPhoneNumber, 0, 6);
        gridPane.add(txtPhoneNumber, 1, 6);

        vbox.getChildren().add(gridPane);

        // TableView for displaying PlayerAndGame data
        TableView<PlayerGameRecord> tableView = new TableView<>();

        TableColumn<PlayerGameRecord, String> colGameTitle = new TableColumn<>("Game Title");
        colGameTitle.setCellValueFactory(data -> data.getValue().gameTitleProperty());

        TableColumn<PlayerGameRecord, Integer> colScore = new TableColumn<>("Score");
        colScore.setCellValueFactory(data -> data.getValue().scoreProperty().asObject());

        TableColumn<PlayerGameRecord, String> colDate = new TableColumn<>("Date");
        colDate.setCellValueFactory(data -> data.getValue().dateProperty());

        tableView.getColumns().addAll(colGameTitle, colScore, colDate);
        tableView.setPrefHeight(200);

        vbox.getChildren().add(tableView);

        // Load players into ComboBox
        loadPlayers(cmbPlayer);

        // Button to refresh players
        btnRefresh.setOnAction(e -> {
            loadPlayers(cmbPlayer);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Players list refreshed successfully!");
        });

        // Load report data when the button is clicked
        btnLoad.setOnAction(e -> {
            String selectedPlayer = cmbPlayer.getSelectionModel().getSelectedItem();
            if (selectedPlayer == null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please select a player to view the report!");
                return;
            }

            int playerId = Integer.parseInt(selectedPlayer.split(":")[0]);
            loadPlayerDetails(playerId, txtFirstName, txtLastName, txtAddress, txtPostalCode, txtProvince, txtPhoneNumber);
            loadPlayerGameRecords(playerId, tableView);
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

    private static void loadPlayerDetails(int playerId, Label txtFirstName, Label txtLastName, Label txtAddress, Label txtPostalCode, Label txtProvince, Label txtPhoneNumber) {
        try (Connection connection = ConnectionDatabase.connection()) {
            String sql = "SELECT first_name, last_name, address, postal_code, province, phone_number FROM Player WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                txtFirstName.setText(resultSet.getString("first_name"));
                txtLastName.setText(resultSet.getString("last_name"));
                txtAddress.setText(resultSet.getString("address"));
                txtPostalCode.setText(resultSet.getString("postal_code"));
                txtProvince.setText(resultSet.getString("province"));
                txtPhoneNumber.setText(resultSet.getString("phone_number"));
            } else {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Player not found!");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not load player details: " + e.getMessage());
        }
    }

    private static void loadPlayerGameRecords(int playerId, TableView<PlayerGameRecord> tableView) {
        try (Connection connection = ConnectionDatabase.connection()) {
            String sql = "SELECT g.game_title, pg.score, pg.playing_date " +
                    "FROM PlayerAndGame pg " +
                    "JOIN Game g ON pg.game_id = g.game_id " +
                    "WHERE pg.player_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            ObservableList<PlayerGameRecord> records = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String gameTitle = resultSet.getString("game_title");
                int score = resultSet.getInt("score");
                String date = resultSet.getDate("playing_date").toString();
                records.add(new PlayerGameRecord(gameTitle, score, date));
            }
            tableView.setItems(records);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not load player game records: " + e.getMessage());
        }
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
