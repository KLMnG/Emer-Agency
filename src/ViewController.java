import java.util.List;

public class ViewController {
    private ComplaintController complaintController;
    private UserController userController;
    private View view;
    private User currentUser;

    public ViewController(View v) {
        this.view = v;
    }


    public void addUserToActionTable(User rowData) {
        if (!this.view.getUsersToAction().contains(rowData))
            this.view.addUserTOActionList(rowData);
    }

    public void addComplaitController(ComplaintController complaintController) {
        this.complaintController = complaintController;
    }

    public void addUserController(UserController userController) {
        this.userController = userController;
    }


    public void giveOrder() {
        try {
            this.userController.makeNewOrder(this.view.getUsersToAction(), this.currentUser.getId(), this.view.getTaDetailsText());
        } catch (Exception e) {
            this.view.showError(e.getMessage());
        }
    }

    public void enterComplaint(){
        try {
            for (User complainant : this.view.getUsersToAction()) {
                this.complaintController.makeNewComplaint(complainant,this.currentUser,this.view.getTaDetailsText());
            }
        } catch (Exception e) {
            this.view.showError(e.getMessage());
        }
    }


    public void getUsers() {
        List<User> users = userController.getOrderRelevantUsers();
        view.setRelevantUsersOrderTable(users);
    }

    public void connectUser(User user) {
        currentUser = user;
        this.view.setLbUserNameText(user.getName());
    }


}
