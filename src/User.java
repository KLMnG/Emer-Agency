import java.util.ArrayList;

public class User {

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
