/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Bill;
import java.util.List;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BillDAO implements CrudOperations<Bill> {

    @Override
    public boolean add(Bill obj) {
        try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "INSERT INTO bills VALUES(?,?,?,?,?,?)";

        PreparedStatement pst =
                con.prepareStatement(sql);

        pst.setString(1, obj.getBillId());
        pst.setString(2, obj.getAppointmentId());

        pst.setDouble(3, obj.getHospitalBill());
        pst.setDouble(4, obj.getLabFee());
        pst.setDouble(5, obj.getTotalAmount());

        pst.setString(6, obj.getBillDate());

        return pst.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
        
        return false;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String generateBillId() {

    String billId = "B1";

    try {

        Connection con =
                DBConnection.getConnection();

        String sql =
                "SELECT bill_id FROM bills "
                + "ORDER BY bill_id DESC LIMIT 1";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            String lastId =
                    rs.getString("bill_id");

            int number =
                    Integer.parseInt(lastId.substring(1));

            billId =
                    "B" + (number + 1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return billId;
}

    @Override
    public boolean update(Bill obj) {
       return false; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) {
         return false;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
