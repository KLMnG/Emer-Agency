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
        Parent root = loader.load(getClass().getResource("EmerAgencyFXML.fxml").openStream());
        View view = loader.getController();
        ViewController viewController = new ViewController(view);
        ComplaintController complaintController = new ComplaintController();
        UserController userController = new UserController();
        viewController.addComplaitController(complaintController);
        viewController.addUserController(userController);
        view.setViewController(viewController);


        Scene scene = new Scene(root,800,700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
