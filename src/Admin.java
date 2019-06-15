import java.util.ArrayList;

public class Admin extends User {

    private ArrayList<Complaint>ComplaintsToReview;

    public Admin(int rank, int id, String name) {
        super(rank, id, name);
        this.ComplaintsToReview=new ArrayList<>();
    }

    public void addToComplaintsList(Complaint c) {
        ComplaintsToReview.add(c);
    }

    public void approve(Complaint c) {

    }

    public ArrayList<Complaint> getComplaintsToReview() {
        return ComplaintsToReview;
    }
}
