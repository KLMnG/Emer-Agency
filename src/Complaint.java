public class Complaint {

    public String status;
    public String details;
    public User complainant;
    public Warning warning;
    public User accuser;
    public Admin approver;
    

    /*
    public Complaint(String status, String details){
        this.status =status;
        this.details = details;
    }
    */
    public void createComplaint(String details, User complainant, User accuser){
        this.details=details;
        this.complainant = complainant;
        this.accuser=accuser;
        accuser.addComplaint(this);
        complainant.addToAccuserList(this);

    }

    public void createAndSendWarning(User complainant){
        warning = new Warning();
        //take from database
        warning.create(this,complainant, 0 );

    }

    public void setStatus(String approve) {
        this.status = approve;
    }
}
