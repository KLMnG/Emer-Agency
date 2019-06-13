import java.util.ArrayList;

public class User {

    private int rank;
    private int id;
    private String name;


    public User(int rank, int id, String name) {
        this.rank = rank;
        this.id = id;
        this.name = name;
    }

    public void addOrder(Order newOrder) {
    }

    public Department getDepartment() {
        return null;
    }

    public int getID() {
        return 0;
    }

    public ArrayList<Warning>warningList;
    public ArrayList<Complaint>complaintsList;
    public ArrayList<Complaint>accusersList;
    ComplaintController complaintController;


    public void sendWarning(Warning warning) {
        warningList.add(warning);

    }

    public void addComplaint(Complaint complaint) {
        complaintsList.add(complaint);
    }

    public void addToAccuserList(Complaint complaint) {
        accusersList.add(complaint);
    }
}
