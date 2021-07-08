package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private ChoiceBox<Types.UserSearchType> userChoiceBox;

    @FXML
    private ListView<UserModel> userListView;

    @FXML
    private TextField usersTxtName;

    @FXML
    private TextField usersTxtUsername;

    @FXML
    private TextField usersTxtPassword;

    @FXML
    private ChoiceBox<Types.newUserRole> usersComboNewRole;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnClearUser;

    @FXML
    private ChoiceBox<Types.ClassroomSearchType> classroomChoiceBox;

    @FXML
    private ListView<ClassRoomModel> classroomListView;

    @FXML
    private TextField classroomTxtName;

    @FXML
    private TextField classroomTxtLabel;

    @FXML
    private TextField classroomTxtDivision;

    @FXML
    private Button btnAddClassroom;

    @FXML
    private Button btnClearClassroom;

    @FXML
    private TextField classroomTxtStudentID;

    @FXML
    private TextField classroomTxtClassID;

    @FXML
    private Button btnClassroomAssign;

    @FXML
    private Button btnClearClassroomAssign;

    private final Admin model;

    public AdminController(Admin model, Stage stage){
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
        userChoiceBox.getItems().setAll(Types.UserSearchType.values());
        userChoiceBox.getSelectionModel().selectFirst();
        usersComboNewRole.getItems().setAll(Types.newUserRole.values());
        usersComboNewRole.getSelectionModel().selectLast();
        classroomChoiceBox.getItems().setAll(Types.ClassroomSearchType.values());
        classroomChoiceBox.getSelectionModel().selectFirst();
    }

    @FXML
    void onAddClassroom(ActionEvent event) {
        if (!classroomTxtName.getText().isEmpty() && !classroomTxtLabel.getText().isEmpty() && !classroomTxtDivision.getText().isEmpty()){
            model.addNewClassRoom(classroomTxtName.getText(), Integer.parseInt(classroomTxtLabel.getText()), classroomTxtDivision.getText());
            ClearClassroomFields();
        }
    }

    @FXML
    void onAddClassroomClear(ActionEvent event) {
        ClearClassroomFields();
    }

    @FXML
    void onClassroomDelete(ActionEvent event) {

    }

    @FXML
    void onClassroomEdit(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_classroom.fxml"));
            Parent root = loader.load();
            EditClassroomController editClassroomController = loader.getController();
            editClassroomController.initData(model, classroomListView.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onClassroomFindAll(ActionEvent event) {
        classroomListView.getItems().setAll(model.findAllClassrooms());
    }

    @FXML
    void onClassroomSearch(ActionEvent event) {
        String param = ((TextField)event.getSource()).getText();
        classroomListView.getItems().setAll(model.searchClassroom(classroomChoiceBox.getValue(), param));
    }

    @FXML
    void onUserSearch(ActionEvent event) {
        String param = ((TextField)event.getSource()).getText();
        userListView.getItems().setAll(model.search(userChoiceBox.getValue(), param));
    }

    @FXML
    void onUsersDelete(ActionEvent event) {

    }

    @FXML
    void onUsersEdit(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_user.fxml"));
            Parent root = loader.load();
            EditUserController editUserController = loader.getController();
            editUserController.initData(model, userListView.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onUsersFindAll(ActionEvent event) {
        userListView.getItems().setAll(model.findAll());
    }

    @FXML
    void onAddClear(ActionEvent event) {
        ClearUsersFields();
    }

    @FXML
    void onAddUser(ActionEvent event) {
        int txtUserRole = usersComboNewRole.getSelectionModel().getSelectedIndex();
        if (!usersTxtName.getText().isEmpty() && !usersTxtUsername.getText().isEmpty() && !usersTxtPassword.getText().isEmpty()){
            model.addNewUser(usersTxtName.getText(), usersTxtUsername.getText(), usersTxtPassword.getText(), txtUserRole);
            ClearUsersFields();
        }
    }

    @FXML
    void onClassroomAssign(ActionEvent event) {
        if (!classroomTxtClassID.getText().isEmpty() && !classroomTxtStudentID.getText().isEmpty()){
            if(model.assignClass(Integer.parseInt(classroomTxtClassID.getText()), Integer.parseInt(classroomTxtStudentID.getText()))){
                System.out.println("Student have been assigned to class.");
            }
            ClearUsersFields();
        }
    }

    @FXML
    void onClearClassroomAssign(ActionEvent event) {
        ClearClassroomAssignFields();
    }

    private void ClearUsersFields() {
        usersTxtName.setText("");
        usersTxtUsername.setText("");
        usersTxtPassword.setText("");
    }

    private void ClearClassroomFields() {
        classroomTxtName.setText("");
        classroomTxtLabel.setText("");
        classroomTxtDivision.setText("");
    }

    private void ClearClassroomAssignFields() {
        classroomTxtStudentID.setText("");
        classroomTxtClassID.setText("");
    }

}
