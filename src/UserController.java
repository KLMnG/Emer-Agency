import java.util.ArrayList;
import java.util.List;

public class UserController {



    private List<User> users;


    public UserController() {
        // get all users

    }

    public void makeNewOrder(List<User> lu, int uID, String details) {
        User user=findUser(uID);

        if(user!=null && !lu.contains(user)) {
            Order newOrder = new Order(user, details);
            newOrder.addOrderedUsers(lu);
            for (User ordered : lu) {
                ordered.addOrder(newOrder);
            }
            user.addOrder(newOrder);
            Model.getInstance().createOrder(newOrder);
        }

    }

    private User findUser(int uID) {
        for (User user :
                this.users) {
            if (user.getId() == uID)
                return user;
        }
        return null;
    }

    public List<User> getDepartmentMemebersOf(User u){
        List<User> members=new ArrayList<>();
        for (User user :
                this.users) {
            if(user.getDepartment().equals(u.getDepartment()))
                members.add(user);
        }
        return members;
    }



}
