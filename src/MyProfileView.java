import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class MyProfileView {
    public ListView myWarnings;
    public ListView myOrders;
    public ListView myCommands;
    private ViewController viewController;

    public void initialize(){


        myWarnings.setCellFactory(param -> {
            ListCell<Warning> cell = new ListCell<Warning>(){
                @Override
                protected void updateItem(Warning item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getComplaint() == null) {
                        setText(null);
                    } else {
                        setText(item.getComplaint().getId() + "");
                    }
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!cell.isEmpty())) {
                    Warning warning = cell.getItem();
                    new Alert(Alert.AlertType.INFORMATION,warning.getComplaint().getDetails()).show();
                }
            });

            return cell;
        });

        myOrders.setCellFactory(param -> {
            ListCell<Order> cell = new ListCell<Order>(){
                @Override
                protected void updateItem(Order item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getId() + "");
                    }
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!cell.isEmpty())) {
                    Order order = cell.getItem();
                    new Alert(Alert.AlertType.INFORMATION,order.getDetails()).show();
                }
            });
            return cell;
        });

        myCommands.setCellFactory(param -> {
            ListCell<Order> cell = new ListCell<Order>(){
                @Override
                protected void updateItem(Order item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getId() + "");
                    }
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!cell.isEmpty())) {
                    Order order = cell.getItem();
                    new Alert(Alert.AlertType.INFORMATION,order.getDetails()).show();
                }
            });
            return cell;
        });
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
        initializeView();
    }

    private void initializeView() {

    }

    public void updateUserDetails(){
        User user = this.viewController.getCurrentUser();
        this.myWarnings.setItems(FXCollections.observableArrayList(user.getWarningList()));
        this.myOrders.setItems(FXCollections.observableArrayList(user.getOrdersRecieved()));
        this.myCommands.setItems(FXCollections.observableArrayList(user.getOrdersDelivered()));
    }

    public void Back(ActionEvent actionEvent) {
        this.viewController.setView("Main");
    }
}
