import java.util.ArrayList;
import java.util.List;

public class Order {

    private String details;
    private User ordering;
    private List<User> orderedUsers;


    public Order(User u,String s){
        this.ordering=u;
        this.details=s;
        orderedUsers=new ArrayList<>();
    }

    public void addOrderedUsers(List<User> users){
        orderedUsers.addAll(users);
    }



}
