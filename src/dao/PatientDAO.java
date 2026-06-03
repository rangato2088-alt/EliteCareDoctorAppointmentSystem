/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Patient;

public class PatientDAO {
    
    public boolean addPatient(Patient patient) {

    try {

        Connection con = DBConnection.getConnection();

        String sql =
            "INSERT INTO patients(patient_id,nic,patient_name,age,gender,contact_number,address) VALUES(?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, patient.getPatientId());
        ps.setString(2, patient.getNic());
        ps.setString(3, patient.getPatientName());
        ps.setInt(4, patient.getAge());
        ps.setString(5, patient.getGender());
        ps.setString(6, patient.getContactNumber());
        ps.setString(7, patient.getAddress());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
}
    public String generatePatientId() {

    String patientId = "P1";

    try {

        Connection con = DBConnection.getConnection();

        String sql =
            "SELECT patient_id FROM patients ORDER BY patient_id DESC LIMIT 1";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            String lastId = rs.getString("patient_id");

            int number =
                Integer.parseInt(lastId.substring(1));

            patientId = "P" + (number + 1);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return patientId;
}
    
    public List<Patient> getAllPatients() {

    List<Patient> patients = new ArrayList<>();

    try {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM patients";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Patient patient = new Patient();

            patient.setPatientId(rs.getString("patient_id"));
            patient.setNic(rs.getString("nic"));
            patient.setPatientName(rs.getString("patient_name"));
            patient.setAge(rs.getInt("age"));
            patient.setGender(rs.getString("gender"));
            patient.setContactNumber(rs.getString("contact_number"));
            patient.setAddress(rs.getString("address"));

            patients.add(patient);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return patients;
}
    
}
