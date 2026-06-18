/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import db.DBConnection;
import model.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    
    public boolean addDoctor(Doctor doctor) {

    try {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO doctors "
                + "(doctor_id, doctor_name, specialization, phone, status, consultation_fee) "
                + "VALUES (?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, doctor.getDoctorId());
        ps.setString(2, doctor.getDoctorName());
        ps.setString(3, doctor.getSpecialization());
        ps.setString(4, doctor.getPhone());
        ps.setString(5, doctor.getStatus());
        ps.setDouble(6, doctor.getConsultationFee());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
        public List<Doctor> getAllDoctors() {

    List<Doctor> doctors = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM doctors";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Doctor doctor = new Doctor();

            doctor.setDoctorId(rs.getString("doctor_id"));
            doctor.setDoctorName(rs.getString("doctor_name"));
            doctor.setSpecialization(rs.getString("specialization"));
            doctor.setPhone(rs.getString("phone"));
            doctor.setStatus(rs.getString("status"));
            doctor.setConsultationFee(rs.getDouble("consultation_fee"));

            doctors.add(doctor);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return doctors;
}
        
        public String generateDoctorId() {

    String doctorId = "D1";

    try {

        Connection con = DBConnection.getConnection();

        String sql =
        "SELECT doctor_id FROM doctors ORDER BY doctor_id DESC LIMIT 1";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            String lastId = rs.getString("doctor_id");

            int number =
            Integer.parseInt(lastId.substring(1));

            doctorId = "D" + (number + 1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return doctorId;
}
        
        public boolean updateDoctor(Doctor doctor) {

    try {

        Connection con = DBConnection.getConnection();

        String sql =
            "UPDATE doctors SET doctor_name=?, specialization=?, phone=?, status=?, consultation_fee=? WHERE doctor_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, doctor.getDoctorName());
        ps.setString(2, doctor.getSpecialization());
        ps.setString(3, doctor.getPhone());
        ps.setString(4, doctor.getStatus());
        ps.setDouble(5, doctor.getConsultationFee());
        ps.setString(6, doctor.getDoctorId());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
     public boolean deleteDoctor(String doctorId) {

    try {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM doctors WHERE doctor_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, doctorId);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
}
     
     public String getDoctorIdByUsername(String username) {

    String doctorId = "";

    try {

        Connection con = DBConnection.getConnection();

        String sql =
            "SELECT doctor_id FROM doctors "
          + "WHERE doctor_name LIKE ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, "%" + username.replace("D2", "") + "%");

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            doctorId = rs.getString("doctor_id");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return doctorId;
}
     
     public double getDoctorConsultationFee(String doctorId) {

    double fee = 0;

    try {

        String sql =
                "SELECT consultation_fee "
                + "FROM doctors "
                + "WHERE doctor_id = ?";

        java.sql.PreparedStatement ps =
                db.DBConnection.getConnection()
                        .prepareStatement(sql);

        ps.setString(1, doctorId);

        java.sql.ResultSet rs =
                ps.executeQuery();

        if (rs.next()) {

            fee = rs.getDouble(
                    "consultation_fee");
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return fee;
}
     
     
    //To count tatal doctors 
     
     public int getTotalDoctors() {

    int count = 0;

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT COUNT(*) FROM doctors";

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
     
     
   public int getActiveDoctors() {

    int count = 0;

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT COUNT(*) FROM doctors "
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
   
   
   public Doctor searchDoctor(String doctorId) {

    Doctor doctor = null;

    try {

        String sql =
                "SELECT * FROM doctors "
              + "WHERE doctor_id = ?";

        java.sql.PreparedStatement ps =
                db.DBConnection.getConnection()
                .prepareStatement(sql);

        ps.setString(1, doctorId);

        java.sql.ResultSet rs =
                ps.executeQuery();

        if (rs.next()) {

            doctor = new Doctor();

            doctor.setDoctorId(
                    rs.getString("doctor_id"));

            doctor.setDoctorName(
                    rs.getString("doctor_name"));

            doctor.setSpecialization(
                    rs.getString("specialization"));

            doctor.setPhone(
                    rs.getString("phone"));

            doctor.setStatus(
                    rs.getString("status"));

            doctor.setConsultationFee(
                    rs.getDouble("consultation_fee"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return doctor;
}
    
}
