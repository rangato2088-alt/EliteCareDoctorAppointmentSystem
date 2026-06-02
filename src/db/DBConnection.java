/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {

        try {

            if(connection == null || connection.isClosed()) {

                String url =
                    "jdbc:mysql://localhost:3306/elitecare_db";

                connection =
                    DriverManager.getConnection(
                        url,
                        XMLReader.getUser(),
                        XMLReader.getPassword()
                    );

                System.out.println("Database Connected");

            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return connection;
    }
    
}
