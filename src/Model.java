import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model{

    private static Model Instance = new Model();

    public static Model getInstance() {
        return Instance;
    }


    private List<User> users;
    private List<Warning> warnings;
    private List<Complaint> complaints;

    private Model(){
        updateUsers();
        for (User user : users){
            this.updateUserComplaints(user);
        }
        for (User user : users){
            for (Complaint complaint : complaints) {
                this.updateUserWarnings(user,complaint);
            }
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void updateUsers(){
        String sql = "SELECT * FROM Users";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            ResultSet rs = pstmt.executeQuery();
            List<User> tmp = new ArrayList<>();
            while(rs.next()) {
                User user = new User(rs.getInt("Id"),rs.getInt("Rank"),rs.getString("Name"));
                tmp.add(user);
            }
            users = tmp;

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Users From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    private User getUserById(int uId){
        for (User user : users) {
            if (user.getId() == uId)
                return user;
        }
        return null;
    }

    private Complaint getComplaintById(int cId){
        for (Complaint compliant : complaints) {
            if (compliant.getId() == cId)
                return compliant;
        }
        return null;
    }

    public void updateUserComplaints(User u){
        String sql = "SELECT * FROM UsersComplaints WHERE ComplainantId = ?";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1,u.getId());
            ResultSet rs = pstmt.executeQuery();

            List<Complaint> tmp = new ArrayList<>();
            while(rs.next()) {
                int id = rs.getInt("Id");
                User accuser = getUserById(rs.getInt("AccuserId"));
                User complainant = getUserById(rs.getInt("ComplainantId"));
                Admin admin = (Admin)getUserById(rs.getInt("AdminId"));
                String Details = rs.getString("Details");
                String status = rs.getString("Status");
                Complaint complaint = new Complaint(id,status,Details,complainant,null,accuser,admin);
                tmp.add(complaint);
                accuser.addToAccuserList(complaint);
                complainant.addComplaint(complaint);
            }
            this.complaints.addAll(tmp);

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Complaint From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public void updateUserWarnings(User u, Complaint complaint){
        String sql = "SELECT * FROM UsersWarnings WHERE UserId = ? AND ComplaintId = ?";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1,u.getId());
            ResultSet rs = pstmt.executeQuery();

            List<Warning> tmp = new ArrayList<>();
            while(rs.next()) {
                Warning warning = new Warning(complaint, u);
                u.addWarning(warning);
                complaint.setWarning(warning);
                tmp.add(warning);
            }
            this.warnings.addAll(tmp);

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Warning From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public List<User> getDepartmentUsers(Department department){
        List<User> tmp = new ArrayList<>();
        for (User user : users) {
            if (user.getDepartment().getId() == department.getId())
                tmp.add(user);
        }
        return tmp;
    }

    public void createComplaint(Complaint c) {
        String sql = "INSERT INTO UsersComplaints(Id,AccuserId,ComplainantId,Details,Status) VALUES(?,?,?,?,?);";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1,c.getId());
            pstmt.setInt(2,c.getAccuser().getId());
            pstmt.setInt(3,c.getId());
            pstmt.setString(4,c.getDetails());
            pstmt.setString(5,c.getStatus());

            pstmt.executeQuery();

            this.complaints.add(c);
        } catch (SQLException e) {
            System.out.println("Failed Add Complaint To DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public void UpdateComplaintStatus(Complaint c,String status) {
        String sql = "UPDATE UsersComplaints SET Status = ? WHERE Id = ?";


        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1,status);
            pstmt.setInt(2,c.getId());

            pstmt.executeQuery();

            c.setStatus(status);
        } catch (SQLException e) {
            System.out.println("Failed to update Status in DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public void createWarning(Warning w) {
        String sql = "INSERT INTO UsersComplaints(UserId,ComplaintId) VALUES(?,?);";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1,w.getWarned().getId());
            pstmt.setInt(2,w.getComplaint().getId());

            pstmt.executeQuery();

            this.warnings.add(w);

        } catch (SQLException e) {
            System.out.println("Failed Add Warning To DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public int getMaxCompliantId() {
        String sql = "SELECT max(Id) as MaxId FROM UsersComplaints";

        int Id = 0;
        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            ResultSet rs = pstmt.executeQuery();
            Id = (rs.getInt("MaxId"));

        } catch (SQLException e) {
            System.out.println("Failed To get Max Complaint ID From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
        return Id;
    }


}
