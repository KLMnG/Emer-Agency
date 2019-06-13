import java.util.ArrayList;
import java.util.List;

public class UserController {


    private List<Order> orders;
    private List<User> users;


    public UserController() {
        // get all users
        //get all orders


    }


    public void makeNewOrder(List<User> lu, int uID, String details) {
        User user=findUser(uID);

        if(user!=null) {
            Order newOrder = new Order(user, details);
            newOrder.addOrderedUsers(lu);
            this.orders.add(newOrder);
            for (User ordered :
                    lu) {
                ordered.addOrder(newOrder);
            }
            user.addOrder(newOrder);
        }

    }

    private User findUser(int uID) {
        for (User user :
                this.users) {
            if (user.getID() == uID)
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
