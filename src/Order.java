import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private static int IdGenerator = Model.getInstance().getMaxOrderId();

    private String details;
    private User ordering;
    private List<User> orderedUsers;
    private int Id;


    public Order(String details, User ordering, int id) {
        this.details = details;
        this.ordering = ordering;
        this.Id = id;
        orderedUsers=new ArrayList<>();
    }

    public Order(User u, String s){
        this.ordering=u;
        this.details=s;
        this.Id = ++IdGenerator;
        orderedUsers=new ArrayList<>();

    }

    public void addOrderedUsers(List<User> users){
        orderedUsers.addAll(users);
    }


    public User getOrdering() {
        return ordering;
    }

    public String getDetails() {
        return details;
    }

    public List<User> getOrderedUsers() {
        return orderedUsers;
    }

    public int getId() {
        return Id;
    }
}
