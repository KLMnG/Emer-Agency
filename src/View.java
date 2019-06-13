
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.util.EventListener;

public class View implements IView{

    private ViewController viewController;

    public Button btnConnect;
    public Button btnGiveOrder;
    public Button btnEnterComplaint;

    public TableView tvUsers;
    public TableColumn colUserName;
    public TableColumn colUserDepartment;
    public TableColumn colUserRank;

    public Label lbUserName;
    public TableView tvUsersNames;
    public TextArea taDetails;

    private ObservableList<User> users;


    public void connectUser(ActionEvent actionEvent ) {

    }

    public void giveOrder(ActionEvent actionEvent) {

    }

    public void initialize() {
    /*    this.users = FXCollections.observableArrayList();
        // col_from.setEditable(false);
        tvUsersNames.setRowFactory(param -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    this.viewController.addUserToTable();
                }
            });
            return row;
        });

        colUserName.setCellValueFactory(
                new PropertyValueFactory<User, String>("Name")
        );

        colUserDepartment.setCellValueFactory(
                new PropertyValueFactory<User, String>("Dep.")
        );

        colUserRank.setCellValueFactory(
                new PropertyValueFactory<User, String>("Rank")
        );

        this.tvUsers.setItems(users);*/
    }

    public void setLbUserName(String userName) {
        this.lbUserName.setText(userName);
    }


    public void connectUser(javafx.event.ActionEvent actionEvent) {
    }


    public void giveOrder(javafx.event.ActionEvent actionEvent) {
    }
}



