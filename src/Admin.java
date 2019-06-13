import java.util.ArrayList;

public class Admin extends User {

    private ArrayList<Complaint>ComplaintsToReview;



    public void addToComplaintsList(Complaint c) {
        ComplaintsToReview.add(c);
    }

    public void approve(Complaint c) {

    }

}
