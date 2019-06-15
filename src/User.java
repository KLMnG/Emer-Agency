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
    private ArrayList<Warning>warningList;
    private ArrayList<Complaint>complaintsList;
    private ArrayList<Complaint>accusersList;


    public User(int id,int rank, String name) {
        this.rank = rank;
        this.Id = id;
        this.name = name;
        this.warningList = new ArrayList<>();
        this.complaintsList = new ArrayList<>();
        this.accusersList = new ArrayList<>();
        this.ordersDelivered = new ArrayList<>();
        this.ordersRecieved = new ArrayList<>();
        this.complaintsSent=new ArrayList<>();
        this.complaintsRecieved=new ArrayList<>();
    }

    public void assignDepartment(Department d){
        if (department != d && department == null){
            department = d;
            d.assignUser(this);
        }
    }

    public void addOrder(Order newOrder) {
        if (newOrder.getOrdering().getId()== this.Id) {
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

    public String getDepartmentName() {
        return this.department.getName();
    }

    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrdersRecieved() {
        return ordersRecieved;
    }

    public List<Order> getOrdersDelivered() {
        return ordersDelivered;
    }

    public ArrayList<Warning> getWarningList() {
        return warningList;
    }
}
