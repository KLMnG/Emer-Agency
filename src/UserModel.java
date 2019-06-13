import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private List<User> users;

    public UserModel(){
        updateUsers();
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
            if (user.getID() == uId)
                return user;
        }
        return null;
    }

    public void updateUserComplaints(User u){
        String sql = "SELECT * FROM UsersWarnings WHERE ComplainantId = ?";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1,u.getID());
            ResultSet rs = pstmt.executeQuery();

            List<Warning> tmp = new ArrayList<>();
            while(rs.next()) {
                Complaint complaint = new Complaint();
                int id = rs.getInt("Id");
                User accuser = getUserById(rs.getInt("AccuserId"));
                User complainant = getUserById(rs.getInt("ComplainantId"));
                User admin = getUserById(rs.getInt("AdminId"));
                String Details = rs.getString("Details");
                String Status = rs.getString("Status");

                //complaint.createComplaint();
            }

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Users From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public void updateUserWarnings(User u){
        String sql = "SELECT * FROM UsersWarnings WHERE UserId = ?";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1,u.getID());
            ResultSet rs = pstmt.executeQuery();

            List<Warning> tmp = new ArrayList<>();
            while(rs.next()) {
                Warning warning = new Warning();

            }

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Users From DB");
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
}
