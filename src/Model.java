import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Model Instance;

    public static Model getInstance() {
        if (Instance == null)
            Instance = new Model();

        return Instance;
    }


    private List<User> users;
    private List<Warning> warnings;
    private List<Complaint> complaints;
    private List<Order> orders;
    private List<Department> departments;

    private Model() {
        this.users = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.departments = new ArrayList<>();

        updateDepartments();
        updateUsers();


        for (User user : users) {
            this.updateUserComplaints(user);
            this.updateUserOrders(user);
        }
        for (User user : users) {
            for (Complaint complaint : complaints) {
                this.updateUserWarnings(user, complaint);
                if (user instanceof Admin && complaint.getComplainant().getDepartment().getId() == user.getDepartment().getId()) {
                    Admin admin = (Admin)user;
                    admin.addToComplaintsList(complaint);;
                }
            }
        }
        for (Order order : orders) {
            this.UpdateOrdersUser(order);
        }
    }

    private void updateAdminReviewComplaint(Admin user) {
    }

    public List<User> getUsers() {
        return users;
    }

    private User getUserById(int uId) {
        for (User user : users) {
            if (user.getId() == uId)
                return user;
        }
        return null;
    }

    private Department getDepartmentById(int dId) {
        for (Department department : departments) {
            if (department.getId() == dId)
                return department;
        }
        return null;
    }

    private Complaint getComplaintById(int cId) {
        for (Complaint compliant : complaints) {
            if (compliant.getId() == cId)
                return compliant;
        }
        return null;
    }

    public void updateUsers() {
        String sql = "SELECT * FROM Users";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            List<User> tmp = new ArrayList<>();
            while (rs.next()) {
                int departmentId = rs.getInt("Department");
                boolean isAdmin = rs.getBoolean("IsAdmin");
                User user = null;
                if (!isAdmin)
                    user = new User(rs.getInt("Id"), rs.getInt("Rank"), rs.getString("Name"));
                else
                    user = new Admin(rs.getInt("Id"), rs.getInt("Rank"), rs.getString("Name"));

                Department department = getDepartmentById(departmentId);
                user.assignDepartment(department);
                tmp.add(user);
            }
            users = tmp;

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Users From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    private void updateDepartments() {
        String sql = "SELECT * FROM Departments";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            List<Department> tmp = new ArrayList<>();
            while (rs.next()) {
                Department department = new Department(rs.getInt("Id"), rs.getString("Name"));
                tmp.add(department);
            }
            departments = tmp;

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Users From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }


    public void updateUserComplaints(User u) {
        String sql = "SELECT * FROM UsersComplaints WHERE ComplainantId = ?";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, u.getId());
            ResultSet rs = pstmt.executeQuery();

            List<Complaint> tmp = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                User accuser = getUserById(rs.getInt("AccuserId"));
                User complainant = getUserById(rs.getInt("ComplainantId"));
                Admin admin = (Admin) getUserById(rs.getInt("AdminId"));
                String Details = rs.getString("Details");
                String status = rs.getString("Status");
                Complaint complaint = new Complaint(id, status, Details, complainant, null, accuser, admin);
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

    public void updateUserWarnings(User u, Complaint complaint) {
        String sql = "SELECT * FROM UsersWarnings WHERE UserId = ? AND ComplaintId = ?";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, u.getId());
            pstmt.setInt(2, complaint.getId());
            ResultSet rs = pstmt.executeQuery();

            List<Warning> tmp = new ArrayList<>();
            while (rs.next()) {
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

    private void updateUserOrders(User u) {
        String sql = "SELECT * FROM Orders WHERE OrderingId = ?";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, u.getId());
            ResultSet rs = pstmt.executeQuery();

            List<Order> tmp = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                User ordering = getUserById(rs.getInt("OrderingId"));
                String Details = rs.getString("Details");
                Order order = new Order(Details, ordering, id);
                ordering.addOrder(order);
                tmp.add(order);
            }
            this.orders.addAll(tmp);

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Complaint From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    private void UpdateOrdersUser(Order order) {
        String sql = "SELECT * FROM OrdersRecivers WHERE OrderId = ?";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getId());
            ResultSet rs = pstmt.executeQuery();

            List<User> tmp = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("OrderId");
                User ordered = getUserById(rs.getInt("OrderedId"));
                ordered.addOrder(order);
                tmp.add(ordered);
            }
            order.addOrderedUsers(tmp);

        } catch (SQLException e) {
            System.out.println("Failed Retrieve Complaint From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }


    public List<User> getDepartmentUsers(Department department) {
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
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, c.getId());
            pstmt.setInt(2, c.getAccuser().getId());
            pstmt.setInt(3, c.getId());
            pstmt.setString(4, c.getDetails());
            pstmt.setString(5, c.getStatus());

            pstmt.executeUpdate();

            this.complaints.add(c);
        } catch (SQLException e) {
            System.out.println("Failed Add Complaint To DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public void updateComplaintStatus(Complaint c, String status) {
        String sql = "UPDATE UsersComplaints SET Status = ? WHERE Id = ?";


        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, c.getId());

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
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, w.getWarned().getId());
            pstmt.setInt(2, w.getComplaint().getId());

            pstmt.executeUpdate();

            this.warnings.add(w);

        } catch (SQLException e) {
            System.out.println("Failed Add Warning To DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public void createOrder(Order o) {
        String sql = "INSERT INTO Orders(Id,OrderingId,Details) VALUES(?,?,?);";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, o.getId());
            pstmt.setInt(2, o.getOrdering().getId());
            pstmt.setString(3, o.getDetails());

            pstmt.executeUpdate();
            this.orders.add(o);

            for (User user : o.getOrderedUsers()) {
                createOrderUsers(o, user);
            }


        } catch (SQLException e) {
            System.out.println("Failed Add Order To DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    private void createOrderUsers(Order o, User ordered) {

        String sql = "INSERT INTO OrdersRecivers(OrderId,OrderedId) VALUES(?,?);";

        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, o.getId());
            pstmt.setInt(2, ordered.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed Add OrdersRecivers To DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
    }

    public int getMaxCompliantId() {
        String sql = "SELECT max(Id) as MaxId FROM UsersComplaints";

        int Id = 0;
        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            Id = (rs.getInt("MaxId"));

        } catch (SQLException e) {
            System.out.println("Failed To get Max Complaint ID From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
        return Id;
    }

    public int getMaxOrderId() {
        String sql = "SELECT max(Id) as MaxId FROM Oders";

        int Id = 0;
        try (Connection conn = DBConnection.getInstance().getSQLLiteDBConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            Id = (rs.getInt("MaxId"));

        } catch (SQLException e) {
            System.out.println("Failed To get Max Order ID From DB");
            System.out.println("Error: ");
            System.out.println(e.getMessage());
        }
        return Id;
    }


//    public void createAndUpdateOrder(Order newOrder) {
//        createOrder(newOrder);
//        updateUserOrders(newOrder.getOrdering());
//        for (User user :
//                newOrder.getOrderedUsers()){
//         //updateTheOrderedUsers
//        }
//    }
}
