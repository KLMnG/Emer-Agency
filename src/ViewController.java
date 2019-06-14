import java.util.List;

public class ViewController {
    private ComplaintController complaintController;
    private UserController userController;
    private View view;

    public ViewController(View v){
        this.view = v;
    }

    public void connectUser(String id) {

    }

    public void addUserToTable() {

    }

    public void addComplaitController(ComplaintController complaintController) {
        this.complaintController=complaintController;

    }

    public void addUserController(UserController userController) {
        this.userController=userController;
    }


    public void giveOrder() {
        List<User> users = userController.getOrderRelevantUsers();
        view.setRelevantUsersOrderTable(users);
    }
}
