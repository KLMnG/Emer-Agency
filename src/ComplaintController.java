import java.util.ArrayList;

public class ComplaintController {

    public String status;
    public String details;
    public User complainant;
    public Warning warning;
    public User accuser;
    public Admin approver;
    public ArrayList<Complaint> complaintList;
    public ArrayList<User> userList;



    public ComplaintController(){
//        for (User u :userList) {
//            userList.add(u);
//        }
    }

    public void approvedByAdmin(Complaint c, Admin approver, User complainant){
        approver.approve(c);
        manageAComplaint(c,complainant);
    }


    public void manageAComplaint(Complaint c, User complainant){
        c.setStatus("APPROVE");
        c.createAndSendWarning(complainant);
        complaintList.add(c);
    }

    public void makeNewComplaint(User complainant, User accuser, String details) throws Exception {
        Complaint c = new Complaint(complainant,accuser,details);
        //c.setStatus("In Progress");
        Model.getInstance().createComplaint(c);
       // ArrayList <Admin>approvers = getAdmins();
        //for (Admin a:approvers) {
         //   sendToAdmin(c,a);
        }


    public void sendToAdmin(Complaint c, Admin approver){
        this.approver = approver;
        approver.addToComplaintsList(c);

    }



}
