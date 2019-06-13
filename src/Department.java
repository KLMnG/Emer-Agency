import java.util.List;

public class Department {

    protected int Id;
    protected String Name;
    private List<User> members;

    public Department(int Id){
        this.Id = Id;
        updateMembers();

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

    private void updateMembers() {

    }


}
