import java.util.ArrayList;

public class ComplaintController {
    public String status;
    public String details;
    public User complainant;
    public Warning warning;
    public User accuser;
    public Admin approver;
    public ArrayList<Complaint>complaintList;
    public ArrayList<User>userList;

    public ComplaintController(ArrayList<User>userList){
        for (User u :userList) {
            userList.add(u);
        }
    }


    public void manageAComplaint(Complaint c, User complainant){
        c.setStatus("Aprrove");
        c.createAndSendWarning(complainant);
        complaintList.add(c);

    }

    public void makeNewComplanit(User complainant, User accuser, String details){
        Complaint c = new Complaint();
        c.setStatus("In progress");
        c.createComplaint(details,complainant, accuser);


    }



}
