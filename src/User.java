import java.util.ArrayList;
import java.util.List;

public class User {

    private int rank;
    private int id;
    private String name;
    private List<Order> ordersRecieved;
    private List<Order> ordersDelivered;
    private Department department;
    private List<Warning> warnings;
    private List<Complaint> complaintsRecieved;
    private List<Complaint> complaintsSent;
    private UserController userController;
    private ComplaintController complaintController;


    public void addOrder(Order newOrder) {
        if (newOrder.getOrdering() == this) {
            this.ordersDelivered.add(newOrder);
        } else {
            this.ordersRecieved.add(newOrder);
        }
    }

    public Department getDepartment() {
        return this.department;
    }

    public int getID() {
        return this.id;
    }

    public void addWarning(Warning warning) {
        this.warnings.add(warning);

    }

    public void addComplaint(Complaint complaint) {
        if (complaint.getAccucer() == this) {
            this.complaintsSent.add(complaint);
        } else {
            this.complaintsRecieved.add(complaint);
        }
    }

    public void addFeedBack(FeedBack f) {
    }

    public void sendWarning(Warning w) {
        addWarning(w);
    }

    public void addAnswerToFeedBack(String ans,FeedBack f){

    }

}
