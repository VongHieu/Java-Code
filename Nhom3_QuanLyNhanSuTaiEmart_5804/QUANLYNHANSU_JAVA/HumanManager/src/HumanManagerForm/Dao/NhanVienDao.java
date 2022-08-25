/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Dao;

import HumanManagerForm.Connection.DatabaseConnection;
import HumanManagerForm.Model.NhanVien;
import HumanManagerForm.Model.QuanLyTaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vongh
 */
public class NhanVienDao {

    public boolean insert(NhanVien NV) throws Exception {
        String Sql = "insert into NHANVIEN(MANV,HOTENNV,NGAYSINH,GIOITINH,DIACHI,SODIENTHOAI,EMAIL,LUONGCOBAN,NGUOIPHUTHUOC,MaPB,MACV,MAPHUCAP,MATHUE) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, NV.getMANV());
        pm.setString(2, NV.getTENNV());
        pm.setString(3, NV.getNgaySinh());
        pm.setString(4, NV.getGIOITINH());
        pm.setString(5, NV.getDIACHI());
        pm.setString(6, NV.getDIENTHOAI());
        pm.setString(7, NV.getEMAIL());
        pm.setFloat(8, NV.getLUONGCOBAN());
        pm.setInt(9, NV.getNGUOIPHUTHUOC());
        pm.setString(10, NV.getMaPB());
        pm.setString(11, NV.getMaCV());
        pm.setString(12, NV.getMAPHUCAP());
        pm.setString(13, NV.getMaThue());
        return pm.executeUpdate() > 0;
    }

    public boolean Delete(String MANV) throws Exception {
        String sql = "Delete from NHANVIEN where BINARY_CHECKSUM(MANV) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, MANV);
        return pm.executeUpdate() > 0;
    }

    public boolean Update(NhanVien NV) throws Exception {
        String Sql = "Update NHANVIEN SET MANV=?,HOTENNV=?,NGAYSINH=?,GIOITINH=?,DIACHI=?,SODIENTHOAI=?,EMAIL=?,LUONGCOBAN=?,NGUOIPHUTHUOC=?,MAPB=?,MACV=?,MAPHUCAP=?,MATHUE=? WHERE BINARY_CHECKSUM(MANV) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, NV.getMANV());
        pm.setString(2, NV.getTENNV());
        pm.setString(3, NV.getNgaySinh());
        pm.setString(4, NV.getGIOITINH());
        pm.setString(5, NV.getDIACHI());
        pm.setString(6, NV.getDIENTHOAI());
        pm.setString(7, NV.getEMAIL());
        pm.setFloat(8, NV.getLUONGCOBAN());
        pm.setInt(9, NV.getNGUOIPHUTHUOC());
        pm.setString(10, NV.getMaPB());
        pm.setString(11, NV.getMaCV());
        pm.setString(12, NV.getMAPHUCAP());
        pm.setString(13, NV.getMaThue());
        pm.setString(14, NV.getMANV());

        return pm.executeUpdate() > 0;
    }

    public NhanVien Search(String MaNV) throws Exception {
        String Sql = "Select * from NHANVIEN Where BINARY_CHECKSUM(MANV) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, MaNV);
        ResultSet rs = pm.executeQuery();
        if (rs.next()) {
            NhanVien NV = new NhanVien();
            NV.setMANV(rs.getString("MANV"));
            NV.setTENNV(rs.getString("HOTENNV"));
            NV.setNgaySinh(rs.getString("NGAYSINH"));
            NV.setGIOITINH(rs.getString("GIOITINH"));
            NV.setDIACHI(rs.getString("DIACHI"));
            NV.setDIENTHOAI(rs.getString("SODIENTHOAI"));
            NV.setEMAIL(rs.getString("EMAIL"));
            NV.setLUONGCOBAN(rs.getFloat("LUONGCOBAN"));
            NV.setNGUOIPHUTHUOC(rs.getInt("NGUOIPHUTHUOC"));
            NV.setMaPB(rs.getString("MAPB"));
            NV.setMaCV(rs.getString("MACV"));
            NV.setMAPHUCAP(rs.getString("MAPHUCAP"));
            NV.setMaThue(rs.getString("MATHUE"));
            return NV;
        }
        return null;
    }

}
