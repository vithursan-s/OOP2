package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditClassroomController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLevel;

    @FXML
    private TextField txtDivision;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnUpdate;

    private ClassRoomModel classRoomModel;
    private Admin model;

    public void initData(Admin model, ClassRoomModel classRoomModel){
        txtName.setText(classRoomModel.getName());
        txtLevel.setText(String.valueOf(classRoomModel.getLevel()));
        txtDivision.setText(classRoomModel.getDivision());
        this.classRoomModel = classRoomModel;
        this.model = model;
    }

    @FXML
    void onCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onUpdate(ActionEvent event) throws SQLException {
        classRoomModel.setName(txtName.getText());
        classRoomModel.setLevel(Integer.parseInt(txtLevel.getText()));
        classRoomModel.setDivision(txtDivision.getText());
        if (model.updateClassroom(classRoomModel)){
            Stage stage = (Stage) btnUpdate.getScene().getWindow();
            stage.close();
        }
    }

}
