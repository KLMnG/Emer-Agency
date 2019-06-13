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
        complainant.addComplaint(this);
    }

    public void createAndSendWarning(User complainant){
        warning = new Warning();
        warning.create(this,complainant);

    }

    public void setStatus(String aprrove) {
        this.status = aprrove;
    }
}
