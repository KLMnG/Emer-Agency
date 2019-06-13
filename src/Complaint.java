import com.sun.org.apache.xpath.internal.operations.Mod;

public class Complaint {


    private static int IdGenerator = 0;
    private int Id;
    private String status;
    private String details;
    private User complainant;
    private Warning warning;
    private User accuser;
    private Admin approver;


    public static void setIdGenerator(){
        IdGenerator = Model.getInstance().getMaxCompliantId();
    }

    public Complaint(int id, String status, String details, User complainant, Warning warning, User accuser, Admin approver) {
        this.Id = id;
        this.status = status;
        this.details = details;
        this.complainant = complainant;
        this.warning = warning;
        this.accuser = accuser;
        this.approver = approver;
    }

    public Complaint(User complainant, User accuser,String details) throws Exception {
        if(!complainant.equals(accuser)) {
            this.details = details;
            this.complainant = complainant;
            this.accuser = accuser;
            this.Id = ++IdGenerator;
            accuser.addComplaint(this);
            complainant.addToAccuserList(this);
        }
        else{
            throw new Exception("complainant and accuser cant be the same one");
        }
    }

    public void createAndSendWarning(User complainant){
        warning = new Warning(this,complainant);
        Model.getInstance().createWarning(warning);
        warning.create(this,complainant);
    }

    public void setWarning(Warning warning){
        this.warning = warning;
    }

    public void setStatus(String status) {
        Model.getInstance().UpdateComplaintStatus(this,status);
    }

    public User getAccuser() {
        return this.accuser;
    }

    public int getId() {
        return Id;
    }

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public User getComplainant() {
        return complainant;
    }

    public Warning getWarning() {
        return warning;
    }

    public Admin getApprover() {
        return approver;
    }
}
