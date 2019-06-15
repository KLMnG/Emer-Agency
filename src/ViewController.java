import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class ViewController {
    private ComplaintController complaintController;
    private UserController userController;
    private View view;
    private MyProfileView myProfileView;
    private ComplaintManagerView complaintManagerView;
    private User currentUser;
    private Stage primaryStage;
    private Scene mainScene;
    private Scene myProfileScene;
    private Scene complaintManagerScene;

    public ViewController(View v, MyProfileView myProfileView, ComplaintManagerView complaintManagerView, Stage primaryStage, Scene mainScene, Scene myProfileScene, Scene complaintManagerScene) {
        this.view = v;
        this.myProfileView = myProfileView;
        this.complaintManagerView = complaintManagerView;
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
        this.myProfileScene = myProfileScene;
        this.complaintManagerScene = complaintManagerScene;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setView(String name){
        if (name.equals("Main"))
            this.primaryStage.setScene(mainScene);
        else if (name.equals("myProfile")) {
            this.primaryStage.setScene(myProfileScene);
            this.myProfileView.updateUserDetails();
        }
        else if (name.equals("CompliantManager")) {
            this.primaryStage.setScene(complaintManagerScene);
            this.complaintManagerView.updateComplaintsDetails();
        }
    }
}
