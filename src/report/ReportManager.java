/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;

import db.DBConnection;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


public class ReportManager {
    
    public static void showPatientReport() {

        try {

            Connection con = DBConnection.getConnection();

            JasperReport report = JasperCompileManager.compileReport(
                    "src/report/PatientReport.jrxml");

            JasperPrint print = JasperFillManager.fillReport(
                    report,
                    new HashMap<>(),
                    con);

            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
