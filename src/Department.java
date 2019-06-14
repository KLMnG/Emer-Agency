import java.util.ArrayList;
import java.util.List;

public class Department {

    protected int Id;
    protected String Name;
    private List<User> members;

    public Department(int Id,String name){
        this.Id = Id;
        this.Name = name;
        this.members = new ArrayList<>();
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void assignUser(User u){
        if (!members.contains(u)) {
            members.add(u);
            u.assignDepartment(this);
        }
    }

    private void updateMembers() {

    }


}
