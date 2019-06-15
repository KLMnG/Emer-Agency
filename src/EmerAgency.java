import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmerAgency extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Parent mainRoot = loader.load(getClass().getResource("EmerAgencyFXML.fxml").openStream());
        View view = loader.getController();

        loader = new FXMLLoader();
        Parent profileRoot = loader.load(getClass().getResource("MyProfile.fxml").openStream());
        MyProfileView myProfileView = loader.getController();

        Scene mainScene = new Scene(mainRoot,800,700);
        Scene profileScene = new Scene(profileRoot,800,700);

        ViewController viewController = new ViewController(view,myProfileView,primaryStage,mainScene,profileScene);
        ComplaintController complaintController = new ComplaintController();
        UserController userController = new UserController();

        viewController.addComplaitController(complaintController);
        viewController.addUserController(userController);

        view.setViewController(viewController);
        myProfileView.setViewController(viewController);

        viewController.setView("Main");
        primaryStage.show();
    }
}
