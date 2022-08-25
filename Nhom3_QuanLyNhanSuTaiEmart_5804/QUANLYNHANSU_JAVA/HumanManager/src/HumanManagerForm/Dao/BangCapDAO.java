/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Dao;

import HumanManagerForm.Connection.DatabaseConnection;
import HumanManagerForm.Model.BangCap;
import HumanManagerForm.Model.PhongBAn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vongh
 */
public class BangCapDAO {

    public boolean insert(BangCap pb) throws Exception {
        String Sql = "insert into BANGCAP(MABC,MANV,TENBC,LOAIBC,NGAYCAP,DONVICAP) values(?,?,?,?,?,?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pb.getMABC());
        pm.setString(2, pb.getMANV());
        pm.setString(3, pb.getTENBC());
        pm.setString(4, pb.getLOAIBC());
        pm.setString(5, pb.getNGAYCAP());
        pm.setString(6, pb.getDONVICAP());
        return pm.executeUpdate() > 0;
    }

    public boolean Delete(String pb) throws Exception {
        String sql = "Delete from BANGCAP where BINARY_CHECKSUM(MABC) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, pb);
        return pm.executeUpdate() > 0;
    }

    public boolean Update(BangCap pb) throws Exception {
        String Sql = "Update BANGCAP SET MABC=?,MANV=?,TENBC=?,LOAIBC=?,NGAYCAP=?,DONVICAP=? WHERE MABC=?";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pb.getMABC());
        pm.setString(2, pb.getMANV());
        pm.setString(3, pb.getTENBC());
        pm.setString(4, pb.getLOAIBC());
        pm.setString(5, pb.getNGAYCAP());
        pm.setString(6, pb.getDONVICAP());
        pm.setString(7, pb.getMABC());

        return pm.executeUpdate() > 0;
    }

    public BangCap Search(String pb) throws Exception {
        String Sql = "Select * from BANGCAP Where BINARY_CHECKSUM(MABC) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, pb);
        ResultSet rs = pm.executeQuery();
        if (rs.next()) {
            BangCap A = new BangCap();
            A.setMABC(rs.getString("MABC"));
            A.setMANV(rs.getString("MANV"));
            A.setTENBC(rs.getString("TENBC"));
            A.setLOAIBC(rs.getString("LOAIBC"));
            A.setNGAYCAP(rs.getString("NGAYCAP"));
            A.setDONVICAP(rs.getString("DONVICAP"));
            return A;
        }
        return null;
    }
}
