import java.util.ArrayList;
import java.util.List;

public class User {

    private int rank;
    private int Id;
    private String name;
    private List<Order> ordersRecieved;
    private List<Order> ordersDelivered;
    private Department department;
    private List<Warning> warnings;
    private List<Complaint> complaintsRecieved;
    private List<Complaint> complaintsSent;
    private UserController userController;
    private ComplaintController complaintController;
    public ArrayList<Warning>warningList;
    public ArrayList<Complaint>complaintsList;
    public ArrayList<Complaint>accusersList;


    public User(int rank, int id, String name) {
        this.rank = rank;
        this.Id = id;
        this.name = name;
    }

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

    public int getId() {
        return this.Id;
    }

    public void addWarning(Warning warning) {
        this.warnings.add(warning);
    }

    public void addComplaint(Complaint complaint) {
        this.complaintsSent.add(complaint);
    }

    public void addToAccuserList(Complaint complaint) {
        this.complaintsRecieved.add(complaint);
    }

    public void addFeedBack(FeedBack f) {
    }

    public void sendWarning(Warning w) {
        addWarning(w);
    }

    public void addAnswerToFeedBack(String ans,FeedBack f){

    }

}
