package exercise1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlayerGameRecord {
    private final SimpleStringProperty gameTitle;
    private final SimpleIntegerProperty score;
    private final SimpleStringProperty date;

    public PlayerGameRecord(String gameTitle, int score, String date) {
        this.gameTitle = new SimpleStringProperty(gameTitle);
        this.score = new SimpleIntegerProperty(score);
        this.date = new SimpleStringProperty(date);
    }

    public SimpleStringProperty gameTitleProperty() {
        return gameTitle;
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }
}
