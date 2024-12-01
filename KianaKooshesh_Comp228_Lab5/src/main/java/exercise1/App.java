package exercise1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        // Tab for adding new games
        Tab gameTab = new Tab("Add New Game");
        gameTab.setContent(GameForm.createGameForm());
        gameTab.setClosable(false);

        // Tab for adding new players
        Tab playerTab = new Tab("Add New Player");
        playerTab.setContent(PlayerForm.createPlayerForm());
        playerTab.setClosable(false);

        // Tab for adding player-game records
        Tab playerGameTab = new Tab("Add Player Game Record");
        playerGameTab.setContent(PlayerGameForm.createPlayerGameForm());
        playerGameTab.setClosable(false);

        // Tab for updating player details
        Tab updatePlayerTab = new Tab("Update Player");
        updatePlayerTab.setContent(UpdatePlayerForm.createUpdatePlayerForm());
        updatePlayerTab.setClosable(false);

        // Tab for viewing player report
        Tab playerReportTab = new Tab("Player Report");
        playerReportTab.setContent(PlayerReportForm.createPlayerReportForm());
        playerReportTab.setClosable(false);

        // Add tabs to the tab pane
        tabPane.getTabs().addAll(gameTab, playerTab, playerGameTab, updatePlayerTab, playerReportTab);

        // Set up the scene and stage
        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Game and Player Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
