
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.awt.*;

public class View {

    private ViewController viewController;

    private ComplaintController complaintController;
    private UserController userController;
    public Button btnConnect;
    public Button btnGiveOrder;
    public Button btnEnterComplaint;
    public TableView tvUsers;
    public ListView lvUsers;
    public TextArea taDetails;
    ListView<String> listView =new ListView<String>();


    public void connectUser(){
        viewController.connectUser((String)lvUsers.getSelectionModel().getSelectedItem());
    }

    public void giveOrder(){

    }



}
