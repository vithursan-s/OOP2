package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private IUserDAO buildDAO(){
        return new SqliteUserDAO();
    }

    private IClassRoomDAO buildClassroomDAO(){
        return new SqliteClassRoomDAO();
    }

    private Admin buildModel() throws Exception {
        return new Admin(buildDAO(), buildClassroomDAO());
    }

    private AdminController buildController(Stage stage) throws Exception {
        return new AdminController(buildModel(), stage);
    }

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        loader.setControllerFactory(t -> {
            try {
                return buildLoginController(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    private LoginController buildLoginController(Stage stage) throws Exception {
        return new LoginController(buildModel(), stage);
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
