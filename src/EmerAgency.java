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
        ViewController viewController = new ViewController();
        ComplaintController complaintController = new ComplaintController();
        UserController userController = new UserController();
        viewController.addComplaitController(complaintController);
        viewController.addUserControlelr(userController);



        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("EmerAgencyFXML.fxml").openStream());
        IView view = loader.getController();
        Scene scene = new Scene(root,800,700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
