/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Dao;

import HumanManagerForm.Connection.DatabaseConnection;
import HumanManagerForm.Model.ChucVu;
import HumanManagerForm.Model.PhongBAn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vongh
 */
public class PhongBanDAO {

    public boolean insert(PhongBAn pb) throws Exception {
        String Sql = "insert into PHONGBAN(MAPB,TENPB,SDTPB) values(?,?,?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pb.getMaPB());
        pm.setString(2, pb.getTenPB());
        pm.setString(3, pb.getSDT());

        return pm.executeUpdate() > 0;
    }

    public boolean Delete(String pb) throws Exception {
        String sql = "Delete from PHONGBAN where BINARY_CHECKSUM(MAPB) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, pb);
        return pm.executeUpdate() > 0;
    }

    public boolean Update(PhongBAn pb) throws Exception {
        String Sql = "Update PHONGBAN SET MAPB=?,TENPB=?,SDTPB=? WHERE MAPB=?";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pb.getMaPB());
        pm.setString(2, pb.getTenPB());
        pm.setString(3, pb.getSDT());
        pm.setString(4, pb.getMaPB());

        return pm.executeUpdate() > 0;
    }

    public PhongBAn Search(String pb) throws Exception {
        String Sql = "Select * from PHONGBAN Where BINARY_CHECKSUM(MAPB) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pb);
        ResultSet rs = pm.executeQuery();
        if (rs.next()) {
            PhongBAn A = new PhongBAn();
            A.setMaPB(rs.getString("MAPB"));
            A.setTenPB(rs.getString("TENPB"));
            A.setSDT(rs.getString("SDTPB"));
            return A;
        }
        return null;
    }
}
