/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Dao;

import HumanManagerForm.Connection.DatabaseConnection;
import HumanManagerForm.Model.ChamCong;
import HumanManagerForm.Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vongh
 */
public class ChamCongDAO {

    public boolean Insert(ChamCong n) throws Exception {
        String sql = "insert into BANGCHAMCONG(MABCC,MANV,TENNV,TENCV,THANG,NAM,SONGAYLAM,SONGAYPHEP,SONGAYKHONGPHEP,MAPB)values(?,?,?,?,?,?,?,?,?,?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, n.getMABCC());
        pm.setString(2, n.getMANV());
        pm.setString(3, n.getHOTENNV());
        pm.setString(4, n.getTENCV());
        pm.setInt(5, n.getTHANG());
        pm.setInt(6, n.getNAM());
        pm.setInt(7, n.getSONGAYLAM());
        pm.setInt(8, n.getSONGAYPHEP());
        pm.setInt(9, n.getSONGAYKHONGPHEP());
        pm.setString(10, n.getMAPB());
        return pm.executeUpdate() > 0;

    }

    public boolean Delete(String MABCC) throws Exception {
        String sql = "Delete from BANGCHAMCONG where BINARY_CHECKSUM(MABCC) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, MABCC);
        return pm.executeUpdate() > 0;
    }

    public boolean Update(ChamCong NV) throws Exception {
        String Sql = "Update BANGCHAMCONG SET MABCC=?, THANG=?,NAM=?,SONGAYLAM=?,SONGAYPHEP=?,SONGAYKHONGPHEP=? WHERE BINARY_CHECKSUM(MABCC) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, NV.getMABCC());
        pm.setInt(2, NV.getTHANG());
        pm.setInt(3, NV.getNAM());
        pm.setInt(4, NV.getSONGAYLAM());
        pm.setInt(5, NV.getSONGAYPHEP());
        pm.setInt(6, NV.getSONGAYKHONGPHEP());
        pm.setString(7, NV.getMABCC());
        return pm.executeUpdate() > 0;
    }

    public ChamCong Search(String MABCC) throws Exception {
        String Sql = "Select * from BANGCHAMCONG Where BINARY_CHECKSUM(MABCC) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, MABCC);
        ResultSet rs = pm.executeQuery();
        if (rs.next()) {
            ChamCong NV = new ChamCong();
            NV.setMABCC(rs.getString("MABCC"));
            NV.setMANV(rs.getString("MANV"));
            NV.setHOTENNV(rs.getString("TENNV"));
            NV.setTENCV(rs.getString("TENCV"));
            NV.setTHANG(Integer.parseInt(rs.getString("THANG")));
            NV.setNAM(Integer.parseInt(rs.getString("NAM")));
            NV.setSONGAYLAM(Integer.parseInt(rs.getString("SONGAYLAM")));
            NV.setSONGAYPHEP(Integer.parseInt(rs.getString("SONGAYPHEP")));
            NV.setSONGAYKHONGPHEP(Integer.parseInt(rs.getString("SONGAYKHONGPHEP")));
            NV.setMAPB(rs.getString("MAPB"));
            return NV;
        }
        return null;
    }

}
