/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Appointment;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class AppointmentDAO
        implements CrudOperations<Appointment> {

    @Override
    public boolean add(Appointment appointment) {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                "INSERT INTO appointments "
                + "(appointment_id, patient_id, doctor_id, "
                + "appointment_date, appointment_time) "
                + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setString(1,
                appointment.getAppointmentId());

            ps.setString(2,
                appointment.getPatientId());

            ps.setString(3,
                appointment.getDoctorId());

            ps.setString(4,
                appointment.getAppointmentDate());

            ps.setString(5,
                appointment.getAppointmentTime());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
}

    @Override
    public boolean update(Appointment appointment) {
        return false;
    }

    @Override
    public boolean delete(String appointmentId) {
        return false;
    }

    public String generateAppointmentId() {

        String appointmentId = "A1";

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT appointment_id FROM appointments "
                + "ORDER BY appointment_id DESC LIMIT 1";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String lastId =
                    rs.getString("appointment_id");

                int number =
                    Integer.parseInt(lastId.substring(1));

                appointmentId =
                    "A" + (number + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

         return appointmentId;
}
    
    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments =
            new ArrayList<>();

        try {

            Connection con =
                DBConnection.getConnection();

            String sql =
                "SELECT * FROM appointments";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ResultSet rs =
                ps.executeQuery();

            while (rs.next()) {

                Appointment appointment =
                    new Appointment();

                appointment.setAppointmentId(
                    rs.getString("appointment_id"));

                appointment.setPatientId(
                    rs.getString("patient_id"));

                appointment.setDoctorId(
                    rs.getString("doctor_id"));

                appointment.setAppointmentDate(
                    rs.getString("appointment_date"));

                appointment.setAppointmentTime(
                    rs.getString("appointment_time"));

                appointments.add(appointment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appointments;
    }
    
    
}
