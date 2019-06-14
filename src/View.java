
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class View {


    private ViewController viewController;

    public Button btnConnect;
    public Button btnGiveOrder;
    public Button btnEnterComplaint;

    public TableView tvUsersToAction;
    public TableColumn colUserName;
    public TableColumn colUserDepartment;
    public TableColumn colUserRank;

    public Label lbUserName;

    public TableView tvUsersNames;
    public TableColumn unColUserName;
    public TableColumn unColUserDepartment;
    public TableColumn unColUserRank;


    public TextArea taDetails;

    private ObservableList<User> users;
    private ObservableList<User> usersToAction;


    public void initialize() {
        this.users = FXCollections.observableArrayList();
        this.usersToAction = FXCollections.observableArrayList();
        // col_from.setEditable(false);
        tvUsersNames.setRowFactory(param -> {
            TableRow<User> row = new TableRow<User>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    this.viewController.addUserToActionTable(rowData);
                    //  this.usersToAction.add(rowData);
                }
            });
            return row;
        });

        tvUsersToAction.setRowFactory(param -> {
            TableRow<User> row = new TableRow<User>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    this.usersToAction.remove(rowData);
                    //  this.usersToAction.add(rowData);
                }
            });
            return row;
        });


        colUserName.setCellValueFactory(
                new PropertyValueFactory<User, String>("Name")
        );

        colUserDepartment.setCellValueFactory(
                new PropertyValueFactory<User, String>("DepartmentName")
        );

        colUserRank.setCellValueFactory(
                new PropertyValueFactory<User, Integer>("Rank")
        );

        this.tvUsersNames.setItems(users);


        unColUserName.setCellValueFactory(
                new PropertyValueFactory<User, String>("Name")
        );

        unColUserDepartment.setCellValueFactory(
                new PropertyValueFactory<User, String>("DepartmentName")
        );

        unColUserRank.setCellValueFactory(
                new PropertyValueFactory<User, Integer>("Rank")
        );
        this.tvUsersToAction.setItems(usersToAction);

    }

    public ObservableList<User> getUsersToAction() {
        return this.usersToAction;
    }

    public void addUserTOActionList(User user) {
        this.usersToAction.add(user);
    }

    public void setLbUserName(String userName) {
        this.lbUserName.setText(userName);
    }


    public void connectUser(javafx.event.ActionEvent actionEvent) {
        try {
            this.viewController.connectUser(((User) this.tvUsersNames.getSelectionModel().getSelectedItem()));
        } catch (Exception e) {
            showError("PLEASE CHOOSE A USER");
        }

    }

    public Label getLbUserName() {
        return this.lbUserName;
    }

    public void giveOrder(javafx.event.ActionEvent actionEvent) {
        this.viewController.giveOrder();
    }


    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
        initializeView();
    }

    private void initializeView() {
        this.viewController.getUsers();
    }

    public void setRelevantUsersOrderTable(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
    }


    public void setLbUserNameText(String name) {
        this.lbUserName.setText(name);
    }

    public String getTaDetailsText() {
        return this.taDetails.getText();
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }

    public void enterComplaint(ActionEvent actionEvent) {
        this.viewController.enterComplaint();
    }
}



