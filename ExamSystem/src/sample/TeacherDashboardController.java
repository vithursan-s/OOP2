package sample;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class TeacherDashboardController {
    @FXML
    private Label lblUsername;

    @FXML
    private TextField txtExamName;

    @FXML
    private TextField txtExamActivity;

    @FXML
    private TextField txtQuestionAmount;

    @FXML
    private TextField txtExamLogin;

    @FXML
    private TextField txtExamPasscode;

    @FXML
    private DatePicker examDatePicker;


    public TeacherDashboardController(Teacher buildModel, Stage stage) {
    }


    @FXML
    void onAddExam(ActionEvent event) {

    }

    @FXML
    void onExamTab(ActionEvent event) {

    }

    @FXML
    void onFeedbackTab(ActionEvent event) {

    }

    @FXML
    void onLogOut(ActionEvent event) {

    }

    @FXML
    void onMarksTab(ActionEvent event) {

    }

    @FXML
    void onStatsTab(ActionEvent event) {

    }

}
