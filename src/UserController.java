import java.util.ArrayList;
import java.util.List;

public class UserController {


    private List<User> users;


    public UserController() {
        users = Model.getInstance().getUsers();

    }

    public void makeNewOrder(List<User> lu, int uID, String details) throws Exception {
        User user = findUser(uID);
        checkOrder(user, lu);

        if (user != null && !lu.contains(user)) {
            Order newOrder = new Order(user, details);
            newOrder.addOrderedUsers(lu);
            for (User ordered : lu) {
                ordered.addOrder(newOrder);
            }
            Model.getInstance().createOrder(newOrder);
            user.addOrder(newOrder);


        }

    }

    private void checkOrder(User user, List<User> lu) throws Exception {
        for (User ordered :
                lu) {
            if (user.equals(ordered))
                throw new Exception("Cant Order Yourself!");
        }
        for (User ordered :
                lu) {
            if (!user.getDepartment().equals(ordered.getDepartment()))
                throw new Exception("Ordered User Must Be In The Same Department!");
        }
        for (User ordered :
                lu) {
            if (user.getRank() < ordered.getRank())
                throw new Exception("Ordering User Must Have A Higher Rank!");
        }
    }

    private User findUser(int uID) {
        for (User user : this.users) {
            if (user.getId() == uID)
                return user;
        }
        return null;
    }

    public List<User> getDepartmentMemebersOf(User u) {
        List<User> members = new ArrayList<>();
        for (User user :
                this.users) {
            if (user.getDepartment().equals(u.getDepartment()))
                members.add(user);
        }
        return members;
    }


    public List<User> getOrderRelevantUsers() {
        List tmp = new ArrayList(users);
        return tmp;
    }


}
