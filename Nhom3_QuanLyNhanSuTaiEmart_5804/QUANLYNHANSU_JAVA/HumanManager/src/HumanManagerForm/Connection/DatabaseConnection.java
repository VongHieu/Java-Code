/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vongh
 */
public class DatabaseConnection {

    public static Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String ConnectionURL = "jdbc:sqlserver://HIEUCOMPUTER:1433;database=QUANLYNHANSU";
        String username = "sa";
        String password = "110902";
        Connection conn = DriverManager.getConnection(ConnectionURL, username, password);
        return conn;
    }
    public static void CloseConnection(Connection conn)
    {
        if(conn!=null)
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                        
            }
        }
    } 

}
