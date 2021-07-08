package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditUserController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdate;

    private UserModel userModel;
    private Admin model;

    public void initData(Admin model, UserModel userModel){
        txtName.setText(userModel.getName());
        txtUsername.setText(userModel.getUsername());
        txtPassword.setText(userModel.getPassword());
        this.userModel = userModel;
        this.model = model;
    }

    @FXML
    void onCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onUpdate(ActionEvent event) throws SQLException {
        userModel.setName(txtName.getText());
        userModel.setUsername(txtUsername.getText());
        userModel.setPassword(txtPassword.getText());
        if (model.updateUser(userModel)){
            Stage stage = (Stage) btnUpdate.getScene().getWindow();
            stage.close();
        }
    }
}