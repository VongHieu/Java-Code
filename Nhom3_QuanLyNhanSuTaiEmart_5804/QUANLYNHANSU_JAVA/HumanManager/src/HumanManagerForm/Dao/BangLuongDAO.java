/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Dao;

import HumanManagerForm.Connection.DatabaseConnection;
import HumanManagerForm.Model.BangLuong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vongh
 */
public class BangLuongDAO {

    public boolean insert(BangLuong bl) throws Exception {
        String sql = "insert into BANGLUONG(MABL,MANV,TENNV,TENCV,THANG,NAM,MucluongCB,SoNgayCong,TongPC,TienBH,SoTienThue,TamUng,LuongTH,NgayNhan) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, bl.getMABL());
        pm.setString(2, bl.getMANV());
        pm.setString(3, bl.getTENNV());
        pm.setString(4, bl.getCHUCVU());
        pm.setInt(5, bl.getTHANG());
        pm.setInt(6, bl.getNAM());
        pm.setFloat(7, bl.getMucluongCB());
        pm.setInt(8, bl.getSONGAYCONG());
        pm.setFloat(9, bl.getTongPC());
        pm.setFloat(10, bl.getTienBH());
        pm.setFloat(11, bl.getSoTienThue());
        pm.setFloat(12, bl.getTamUng());
        pm.setFloat(13, bl.getLuongTH());
        pm.setString(14, bl.getNGAYNHAN());
        return pm.executeUpdate() > 0;
    }

    public boolean Delete(String MABL) throws Exception {
        String sql = "Delete from BANGLUONG where BINARY_CHECKSUM(MABL) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, MABL);
        return pm.executeUpdate() > 0;
    }

    public BangLuong Search(String MABCC) throws Exception {
        String Sql = "Select * from BANGLUONG Where BINARY_CHECKSUM(MABL) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, MABCC);
        ResultSet rs = pm.executeQuery();
        if (rs.next()) {
            BangLuong NV = new BangLuong();
            NV.setMABL(rs.getString("MABL"));
            NV.setMANV(rs.getString("MaNV"));
            NV.setTENNV(rs.getString("TenNV"));
            NV.setCHUCVU(rs.getString("TenCV"));
            NV.setTHANG(Integer.parseInt(rs.getString("THANG")));
            NV.setNAM(Integer.parseInt(rs.getString("NAM")));
            NV.setMucluongCB(rs.getFloat("MucluongCB"));
            NV.setSONGAYCONG(rs.getInt("SoNgayCong"));
            NV.setTongPC(rs.getFloat("TongPC"));
            NV.setTienBH(rs.getFloat("TienBH"));
            NV.setSoTienThue(rs.getFloat("SoTienThue"));
            NV.setTamUng(rs.getFloat("TamUng"));
            NV.setLuongTH(rs.getFloat("LuongTH"));
            NV.setNGAYNHAN(rs.getString("NgayNhan"));
            return NV;
        }
        return null;
    }
}
