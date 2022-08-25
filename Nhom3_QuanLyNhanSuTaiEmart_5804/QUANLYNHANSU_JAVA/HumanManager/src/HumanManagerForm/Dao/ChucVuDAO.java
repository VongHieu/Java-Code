/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Dao;

import HumanManagerForm.Connection.DatabaseConnection;
import HumanManagerForm.Model.ChucVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vongh
 */
public class ChucVuDAO {

    public boolean insert(ChucVu cv) throws Exception {
        String Sql = "insert into CHUCVU(MACV,TENCV) values(?,?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, cv.getMaCV());
        pm.setString(2, cv.getTenCV());
        return pm.executeUpdate() > 0;
    }

    public boolean Delete(String cv) throws Exception {
        String sql = "Delete from CHUCVU where BINARY_CHECKSUM(MACV) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, cv);
        return pm.executeUpdate() > 0;
    }

    public boolean Update(ChucVu cv) throws Exception {
        String Sql = "Update CHUCVU SET MACV=?,TENCV=? WHERE MACV=?";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, cv.getMaCV());
        pm.setString(2, cv.getTenCV());
        pm.setString(3, cv.getMaCV());

        return pm.executeUpdate() > 0;
    }

    public ChucVu Search(String cv) throws Exception {
        String Sql = "Select * from CHUCVU Where BINARY_CHECKSUM(MACV) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, cv);
        ResultSet rs = pm.executeQuery();
        if (rs.next()) {
            ChucVu A = new ChucVu();
            A.setMaCV(rs.getString("MACV"));
            A.setTenCV(rs.getString("TENCV"));
            return A;
        }
        return null;
    }
}
