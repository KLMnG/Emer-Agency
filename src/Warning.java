public class Warning {

     private Complaint complaint;
     private User warned;

    public Warning(Complaint complaint, User warned) {
        this.complaint = complaint;
        this.warned = warned;
    }

    public void create(Complaint complaint, User complainant) {

        //go to db
        this.warned = complainant;
        this.complaint = complaint;
        complainant.sendWarning(this);
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public User getWarned() {
        return warned;
    }
}
