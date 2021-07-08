package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private ChoiceBox<Types.UserSearchType> choiceBox;

    @FXML
    private ListView<UserModel> listView;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private ChoiceBox<Types.newUserRole> comboNewRole;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnClear;

    private final Admin model;

    public Controller(Admin model, Stage stage){
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
        choiceBox.getItems().setAll(Types.UserSearchType.values());
        choiceBox.getSelectionModel().selectFirst();
        comboNewRole.getItems().setAll(Types.newUserRole.values());
        comboNewRole.getSelectionModel().selectLast();
    }

    public void onSearch(ActionEvent event) {
        String param = ((TextField)event.getSource()).getText();
        listView.getItems().setAll(model.search(choiceBox.getValue(), param));
    }

    public void onFindAll(ActionEvent event) {
        listView.getItems().setAll(model.findAll());
    }


    public void onEdit(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_user.fxml"));
            Parent root = loader.load();
            EditUserController editUserController = loader.getController();
            editUserController.initData(model, listView.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.getMessage();
        }
    }

    @FXML
    void onAddClear(ActionEvent event) {
        ClearFields();
    }

    private void ClearFields() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    @FXML
    void onAddUser(ActionEvent event) {
        int txtUserRole = comboNewRole.getSelectionModel().getSelectedIndex();
        if (!txtName.getText().isEmpty() && !txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty()){
            model.addNewUser(txtName.getText(), txtUsername.getText(), txtPassword.getText(), txtUserRole);
            ClearFields();
        }
    }
}
