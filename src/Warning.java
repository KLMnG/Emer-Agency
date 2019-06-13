public class Warning {

     public int WarningID;
     public User complainant;
     public Complaint complaint;
    public User warned;



    public void create(Complaint complaint, User complainant) {
        this.complainant = complainant;
        this.complaint = complaint;
        complainant.addWarning(this);

    }







}
