package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentDashboardController {

    @FXML
    private Label lblUsername;

    @FXML
    private TextField txtExamLogin;

    @FXML
    private TextField txtExamPasscode;

    @FXML
    private ListView<ExamModel> stuExamListView;

    @FXML
    private TextArea txtFeedback;

    @FXML
    private TextField txtTotalMarks;

    public StudentDashboardController(Student buildStudentModel, Stage stage) {
    }

    @FXML
    void onClearExamDetails(ActionEvent event) {
        txtFeedback.setText("");
        txtTotalMarks.setText("");
    }

    @FXML
    void onGetExamDetails(ActionEvent event) {

    }

    @FXML
    void onLogOut(ActionEvent event) {

    }

    @FXML
    void onTakeExam(ActionEvent event) {

    }

}
