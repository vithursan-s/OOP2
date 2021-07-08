package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private ComboBox<Types.newUserRole> loginComboBox;


    private final Admin model;

    public LoginController(Admin model, Stage stage) {
        this.model = model;
        stage.setOnCloseRequest(e -> {
            try {
                model.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public void initialize(){
        loginComboBox.getItems().setAll(Types.newUserRole.values());
        loginComboBox.getSelectionModel().selectLast();
    }

    @FXML
    void loginButtonClicked(ActionEvent event) throws IOException {
        Alert alert;
        if (!loginUsername.getText().isEmpty() && !loginPassword.getText().isEmpty()) {
            UserModel user = model.userSignIn(loginUsername.getText(), loginPassword.getText(), loginComboBox.getSelectionModel().getSelectedIndex()+1);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            if (user.getUsername().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR, "Username or Password is incorrect!");
                alert.show();
            } else {
                switch (user.getUserRole()) {
                    case 1 ->
                            //Load up admin
                            loadAdminDashboard();
                    case 2 ->
                            //Load Teacher
                            loadTeacherDashboard();
                    case 3 ->
                            //Load Student
                            loadStudentDashboard();
                }
            }
        }
    }

    private void loadStudentDashboard() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student_dashboard.fxml"));
        loader.setControllerFactory(t -> {
            try {
                return buildStudentController(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }



    private void loadTeacherDashboard() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher_dashboard.fxml"));
        loader.setControllerFactory(t -> {
            try {
                return buildTeacherController(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    private void loadAdminDashboard() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        loader.setControllerFactory(t -> {
            try {
                return buildAdminController(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    private IUserDAO buildUserDAO(){
        return new SqliteUserDAO();
    }

    private IClassRoomDAO buildClassroomDAO(){
        return new SqliteClassRoomDAO();
    }

    private IExamDAO buildExamDAO() {
        return new SqliteExamDAO();
    }

    private IQuestionDAO buildQuestionDAO() {
        return new SqliteQuestionDAO();
    }

    private IFeedbackDAO buildFeedbackDAO() {
        return new SqliteFeedbackDAO();
    }

    private IAnswerDAO buildAnswerDAO() {
        return new SqliteAnswerDAO();
    }

    private Admin buildAdminModel() throws Exception {
        return new Admin(buildUserDAO(), buildClassroomDAO());
    }

    private AdminController buildAdminController(Stage stage) throws Exception {
        return new AdminController(buildAdminModel(), stage);
    }

    private Student buildStudentModel() throws Exception {
        return new Student(buildUserDAO(), buildClassroomDAO(), buildAnswerDAO(), buildFeedbackDAO());
    }

    private StudentDashboardController buildStudentController(Stage stage) throws Exception {
        return new StudentDashboardController(buildStudentModel(), stage);
    }

    private Teacher buildTeacherModel() throws Exception {
        return new Teacher(buildExamDAO(), buildQuestionDAO(), buildFeedbackDAO());
    }

    private TeacherDashboardController buildTeacherController(Stage stage) throws Exception {
        return new TeacherDashboardController(buildTeacherModel(), stage);
    }

}
