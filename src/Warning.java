public class Warning {

     public int WarningID;
     public Complaint complaint;
     public User warned;

    public Warning(){


    }

    public void create(Complaint complaint, User complainant, int warningID) {

        this.warned = complainant;
        this.complaint = complaint;
        this.WarningID = warningID;
        complainant.sendWarning(this);



    }







}
