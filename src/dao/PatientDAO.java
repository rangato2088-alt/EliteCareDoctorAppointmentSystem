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
    
}
