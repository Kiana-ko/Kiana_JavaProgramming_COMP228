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

public class UpdatePlayerForm {

    public static VBox createUpdatePlayerForm() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label lblSelectPlayer = new Label("Select Player:");
        ComboBox<String> cmbPlayer = new ComboBox<>();
        Label lblFirstName = new Label("First Name:");
        TextField txtFirstName = new TextField();
        Label lblLastName = new Label("Last Name:");
        TextField txtLastName = new TextField();
        Label lblAddress = new Label("Address:");
        TextField txtAddress = new TextField();
        Label lblPostalCode = new Label("Postal Code:");
        TextField txtPostalCode = new TextField();
        Label lblProvince = new Label("Province:");
        TextField txtProvince = new TextField();
        Label lblPhoneNumber = new Label("Phone Number:");
        TextField txtPhoneNumber = new TextField();

        Button btnLoad = new Button("Load");
        Button btnUpdate = new Button("Update");

        gridPane.add(lblSelectPlayer, 0, 0);
        gridPane.add(cmbPlayer, 1, 0);
        gridPane.add(btnLoad, 2, 0);
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
        gridPane.add(btnUpdate, 1, 7);

        vbox.getChildren().add(gridPane);

        // Load players into ComboBox
        loadPlayers(cmbPlayer);

        // Button to load player details
        btnLoad.setOnAction(e -> {
            String selectedPlayer = cmbPlayer.getSelectionModel().getSelectedItem();
            if (selectedPlayer == null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please select a player to load!");
                return;
            }
            int playerId = Integer.parseInt(selectedPlayer.split(":")[0]);
            loadPlayerDetails(playerId, txtFirstName, txtLastName, txtAddress, txtPostalCode, txtProvince, txtPhoneNumber);
        });

        // Button to update player details
        btnUpdate.setOnAction(e -> {
            String selectedPlayer = cmbPlayer.getSelectionModel().getSelectedItem();
            if (selectedPlayer == null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please select a player to update!");
                return;
            }

            int playerId = Integer.parseInt(selectedPlayer.split(":")[0]);
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String address = txtAddress.getText();
            String postalCode = txtPostalCode.getText();
            String province = txtProvince.getText();
            String phoneNumber = txtPhoneNumber.getText();

            if (firstName.isEmpty() || lastName.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "First and last name are required!");
                return;
            }

            updatePlayerDetails(playerId, firstName, lastName, address, postalCode, province, phoneNumber);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Player details updated successfully!");
            loadPlayers(cmbPlayer); // Refresh the player list
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

    private static void loadPlayerDetails(int playerId, TextField txtFirstName, TextField txtLastName, TextField txtAddress, TextField txtPostalCode, TextField txtProvince, TextField txtPhoneNumber) {
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

    private static void updatePlayerDetails(int playerId, String firstName, String lastName, String address, String postalCode, String province, String phoneNumber) {
        try (Connection connection = ConnectionDatabase.connection()) {
            String sql = "UPDATE Player SET first_name = ?, last_name = ?, address = ?, postal_code = ?, province = ?, phone_number = ? WHERE player_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, postalCode);
            statement.setString(5, province);
            statement.setString(6, phoneNumber);
            statement.setInt(7, playerId);
            statement.executeUpdate();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Could not update player details: " + e.getMessage());
        }
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
