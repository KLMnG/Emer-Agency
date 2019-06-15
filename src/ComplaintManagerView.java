import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ComplaintManagerView {
    public TableView tvComplaint;
    public TableColumn accuserColumn;
    public TableColumn complainantColumn;

    private ViewController viewController;


    public void initialize() {
        tvComplaint.setRowFactory(param -> {
            TableRow<Complaint> row = new TableRow<Complaint>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Complaint complaint = row.getItem();
                    new Alert(Alert.AlertType.INFORMATION,complaint.getDetails()).show();
                }
            });
            return row;
        });

        accuserColumn.setCellValueFactory(
                new PropertyValueFactory<Complaint, String>("AccuserName")
        );

        complainantColumn.setCellValueFactory(
                new PropertyValueFactory<Complaint, String>("ComplainantName")
        );
    }



    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
        initializeView();
    }

    private void initializeView() {

    }

    public void ApproveComplaint(ActionEvent actionEvent) {
        this.viewController.approveComplaint((Complaint)tvComplaint.getSelectionModel().getSelectedItem());
    }

    public void DenyComplaint(ActionEvent actionEvent) {
        this.viewController.denyComplaint((Complaint)tvComplaint.getSelectionModel().getSelectedItem());
    }

    public void Back(ActionEvent actionEvent) {
        this.viewController.setView("Main");
    }

    public void updateComplaintsDetails(){
        Admin admin = (Admin)this.viewController.getCurrentUser();
        this.tvComplaint.setItems(FXCollections.observableArrayList(admin.getComplaintsToReview()));
    }

}
