import java.util.ArrayList;
import java.util.List;

public class ComplaintController {

    public Admin approver;
    public ArrayList<Complaint> complaintList;
    public ArrayList<User> userList;



    public ComplaintController(){
        Complaint.setIdGenerator();
        complaintList=new ArrayList<>();
//        for (User u :userList) {
//            userList.add(u);
//        }
    }

    public void approvedByAdmin(Complaint c, Admin approver, User complainant){
        approver.removeFromList(c);
        manageAComplaint(c,complainant,"APPROVED");
    }

    public void deniedByAdmin(Complaint c, Admin approver, User complainant){
        approver.removeFromList(c);
        manageAComplaint(c,complainant,"DENIED");
    }


    public void manageAComplaint(Complaint c, User complainant,String Status){
        c.setStatus(Status);
        if (Status.equals("APPROVED")) {
            c.createAndSendWarning(complainant);
        }
    }

    public void makeNewComplaint(User complainant, User accuser, String details) throws Exception {
        Complaint c = new Complaint(complainant,accuser,details);
        //c.setStatus("In Progress");
        complaintList.add(c);
        Model.getInstance().createComplaint(c);
        List<User> approvers = Model.getInstance().getUsers();
        for (User u : approvers) {
            if (u instanceof Admin) {
                Admin admin = (Admin) u;
                if (complainant.getDepartment().getId() == admin.getDepartment().getId())
                    sendToAdmin(c, admin);
            }
        }
    }


    public void sendToAdmin(Complaint c, Admin approver){
        this.approver = approver;
        approver.addToComplaintsList(c);

    }



}
