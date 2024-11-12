
package exercise1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashSet;

public class StudentFormApp extends Application {
    // Responsible for storing selected courses to avoid duplicates:
    private HashSet<String> selectedSubjects = new HashSet<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) {
        // Responsible for setting the title of the main application window to "Student Registration":
        mainWindow.setTitle("Student Registration");

        // Responsible for setting up the root layout using BorderPane:
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));

        // Responsible for arranging input fields in a structured grid layout with spacing and padding:
        GridPane inputForm = new GridPane();
        inputForm.setHgap(10);
        inputForm.setVgap(10);
        inputForm.setPadding(new Insets(10));

        // Responsible for setting uniform row heights in the GridPane with a minimum height of 40:
        for (int i = 0; i < 7; i++) {
            RowConstraints formRow = new RowConstraints();
            formRow.setMinHeight(40);
            inputForm.getRowConstraints().add(formRow);
        }

        // Responsible for creating labels and text fields for student information
        Label labelName = new Label("Name:");
        TextField textName = new TextField();
        Label labelAddress = new Label("Address:");
        TextField textAddress = new TextField();
        Label labelCity = new Label("City:");
        TextField textCity = new TextField();
        Label labelProvince = new Label("Province:");
        TextField textProvince = new TextField();
        Label labelPostalCode = new Label("Postal Code:");
        TextField textPostalCode = new TextField();
        Label labelPhone = new Label("Phone Number:");
        TextField textPhone = new TextField();
        Label labelEmail = new Label("Email:");
        TextField textEmail = new TextField();

        // Responsible for creating checkboxes for additional activities:
        CheckBox checkboxCouncil = new CheckBox("Student Council");
        CheckBox checkboxVolunteer = new CheckBox("Volunteer Work");

        // Responsible for creating radio buttons and a ToggleGroup for major selection:
        ToggleGroup groupMajor = new ToggleGroup();
        RadioButton radioCS = new RadioButton("Computer Science");
        RadioButton radioBusiness = new RadioButton("Business");
        radioCS.setToggleGroup(groupMajor);
        radioBusiness.setToggleGroup(groupMajor);

        // Responsible for aligning major selection radio buttons horizontally:
        HBox boxMajor = new HBox(10, radioCS, radioBusiness);


        /* === ===  Responsible for creating a ComboBox for course
        selection and a ListView for displaying selected course: === == */

        ComboBox<String> dropdownCourses = new ComboBox<>();
        ListView<String> listCourses = new ListView<>();
        // For setting the preferred height for course list:
        listCourses.setPrefHeight(150);

        /* ==  === === === === === === === === === === ===  ===  === === == */


        // Responsible for populating ComboBox with courses based on selected major
        radioCS.setOnAction(e -> {
            dropdownCourses.getItems().clear();
            dropdownCourses.getItems().addAll("Python", "C#", "Java");
        });

        radioBusiness.setOnAction(e -> {
            dropdownCourses.getItems().clear();
            dropdownCourses.getItems().addAll("Economics", "Finance", "Marketing");
        });

        // Responsible for adding a selected course to ListView if not already selected:
        dropdownCourses.setOnAction(e -> {
            String course = dropdownCourses.getValue();
            if (course != null && !selectedSubjects.contains(course)) {
                selectedSubjects.add(course);
                listCourses.getItems().add(course);
            }
        });

        // Responsible for organizing ComboBox and ListView vertically in a VBox:
        VBox boxCourses = new VBox(10, dropdownCourses, listCourses);

        // Responsible for adding all input components to the GridPane:
        inputForm.addRow(0, labelName, textName);
        inputForm.add(labelAddress, 0, 1);
        inputForm.add(textAddress, 1, 1);
        inputForm.add(labelProvince, 0, 2);
        inputForm.add(textProvince, 1, 2);
        inputForm.add(labelCity, 0, 3);
        inputForm.add(textCity, 1, 3);
        inputForm.add(labelPostalCode, 0, 4);
        inputForm.add(textPostalCode, 1, 4);
        inputForm.add(labelPhone, 0, 5);
        inputForm.add(textPhone, 1, 5);
        inputForm.add(labelEmail, 0, 6);
        inputForm.add(textEmail, 1, 6);
        inputForm.add(checkboxCouncil, 2, 1);
        inputForm.add(boxMajor, 3, 0);
        inputForm.add(checkboxVolunteer, 2, 5);
        inputForm.add(boxCourses, 3, 1, 1, 6);

        // == Responsible for creating a TextArea to display all entered information: ==
        TextArea areaDisplay = new TextArea();
        areaDisplay.setEditable(false);
        //  For enabling text wrapping:
        areaDisplay.setWrapText(false);
        // ==  === === === === === === === === === === === === === === === === ===  ==


        //For adjusting the size to enhance readability and scrolling:
        areaDisplay.setPrefSize(1200, 800);


        // Responsible for adding TextArea to a ScrollPane for scrolling if content overflows:
        ScrollPane paneScroll = new ScrollPane(areaDisplay);
        paneScroll.setPrefHeight(200);
        paneScroll.setPrefWidth(600);
        paneScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        paneScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        //Responsible for creating Display button and its action to populate the TextArea with user inputs:
        Button buttonShow = new Button("Display");
        buttonShow.setOnAction(e -> {
            StringBuilder studentInfo = new StringBuilder();
            studentInfo.append(textName.getText()).append(", ");
            studentInfo.append(textAddress.getText()).append(", ");
            studentInfo.append(textCity.getText()).append(", ");
            studentInfo.append(textProvince.getText()).append(", ");
            studentInfo.append(textPostalCode.getText()).append(", ");
            studentInfo.append(textPhone.getText()).append(", ");
            studentInfo.append(textEmail.getText()).append("\n");

            studentInfo.append("Major: ");
            if (radioCS.isSelected()) {
                studentInfo.append("Computer Science\n");
            } else if (radioBusiness.isSelected()) {
                studentInfo.append("Business\n");
            }

            studentInfo.append("Courses: ").append(String.join(", ", selectedSubjects)).append("\n");

            studentInfo.append("Activities: ");
            if (checkboxCouncil.isSelected()) studentInfo.append("Student Council ");
            if (checkboxVolunteer.isSelected()) studentInfo.append("Volunteer Work");

            areaDisplay.setText(studentInfo.toString());
        });


        // Responsible for center-aligning the Display button using an HBox:
        HBox boxButton = new HBox(buttonShow);
        boxButton.setAlignment(Pos.CENTER);
        boxButton.setPadding(new Insets(10));


        // Responsible for organizing the Display button and ScrollPane vertically in a VBox
        VBox boxDisplay = new VBox(10, boxButton, paneScroll);
        boxDisplay.setPadding(new Insets(10));


        // == Responsible for adding input form and display area to the main layout (BorderPane): ==
        // Input form at the center:
        layout.setCenter(inputForm);
        // Display area at the bottom:
        layout.setBottom(boxDisplay);
        // ==  === === === === === === ==== === ==== ===== ====  ==== === === === === === === ===  ==


        // Responsible for setting the scene and displaying the main window:
        mainWindow.setScene(new Scene(layout, 800, 700));
        mainWindow.show();
    }
}