/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User login(String username, String password) {

        User user = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=? AND status='ACTIVE'";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return user;
    }
    
    
    
    public boolean addUser(User user) {

    try {

        Connection con = DBConnection.getConnection();

        String sql =
        "INSERT INTO users(username,password,role,status) VALUES(?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getRole());
        ps.setString(4, user.getStatus());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
}
    public List<User> getAllUsers() {

    List<User> users = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM users";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            User user = new User();

            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setRole(rs.getString("role"));
            user.setStatus(rs.getString("status"));

            users.add(user);
        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return users;
}
    public boolean updateUser(User user) {

    try {

        Connection con = DBConnection.getConnection();

        String sql =
        "UPDATE users SET username=?, password=?, role=?, status=? WHERE user_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getRole());
        ps.setString(4, user.getStatus());
        ps.setInt(5, user.getUserId());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();

    }

    return false;
}
    
    public boolean deleteUser(int userId) {

    try {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM users WHERE user_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, userId);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();

    }

    return false;
}
    
    
    public int getTotalUsers() {

    int count = 0;

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT COUNT(*) FROM users";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return count;
}
    
    
    public int getActiveUsers() {

    int count = 0;

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT COUNT(*) FROM users "
              + "WHERE status = 'ACTIVE'";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return count;
}
    
}
