package exercise1;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlayerForm {

    public static GridPane createPlayerForm() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label lblFirstName = new Label("First Name:");
        Label lblLastName = new Label("Last Name:");
        Label lblAddress = new Label("Address:");
        Label lblPostalCode = new Label("Postal Code:");
        Label lblProvince = new Label("Province:");
        Label lblPhoneNumber = new Label("Phone Number:");

        TextField txtFirstName = new TextField();
        TextField txtLastName = new TextField();
        TextField txtAddress = new TextField();
        TextField txtPostalCode = new TextField();
        TextField txtProvince = new TextField();
        TextField txtPhoneNumber = new TextField();

        Button btnSubmit = new Button("Add Player");

        gridPane.add(lblFirstName, 0, 0);
        gridPane.add(txtFirstName, 1, 0);
        gridPane.add(lblLastName, 0, 1);
        gridPane.add(txtLastName, 1, 1);
        gridPane.add(lblAddress, 0, 2);
        gridPane.add(txtAddress, 1, 2);
        gridPane.add(lblPostalCode, 0, 3);
        gridPane.add(txtPostalCode, 1, 3);
        gridPane.add(lblProvince, 0, 4);
        gridPane.add(txtProvince, 1, 4);
        gridPane.add(lblPhoneNumber, 0, 5);
        gridPane.add(txtPhoneNumber, 1, 5);
        gridPane.add(btnSubmit, 1, 6);

        btnSubmit.setOnAction(e -> {
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

            try (Connection connection = ConnectionDatabase.connection()) {
                String sql = "INSERT INTO Player (player_id, first_name, last_name, address, postal_code, province, phone_number) VALUES (seq_player.NEXTVAL, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, address);
                statement.setString(4, postalCode);
                statement.setString(5, province);
                statement.setString(6, phoneNumber);
                statement.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Player added successfully!");
                txtFirstName.clear();
                txtLastName.clear();
                txtAddress.clear();
                txtPostalCode.clear();
                txtProvince.clear();
                txtPhoneNumber.clear();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Could not add player: " + ex.getMessage());
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
