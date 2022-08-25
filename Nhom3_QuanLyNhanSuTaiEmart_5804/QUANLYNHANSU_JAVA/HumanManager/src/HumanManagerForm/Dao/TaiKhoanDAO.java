/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Dao;

import HumanManagerForm.Connection.DatabaseConnection;
import HumanManagerForm.Model.QuanLyTaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 *
 * @author vongh
 */
public class TaiKhoanDAO {

    public boolean insert(QuanLyTaiKhoan TK) throws Exception {
        String Sql = "insert into DANGNHAP(ID,MATKHAU,MANV) values(?,?,?)";

        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, TK.getId());
        pm.setString(2, TK.getPASS());
        pm.setString(3, TK.getMANV());
        return pm.executeUpdate() > 0;
    }

    public ResultSet Dangnhap(QuanLyTaiKhoan TK) throws Exception {
        String Sql = "Select * from DANGNHAP where BINARY_CHECKSUM(ID) = BINARY_CHECKSUM (?) and BINARY_CHECKSUM(MATKHAU) = BINARY_CHECKSUM(?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, TK.getId());
        pm.setString(2, TK.getPASS());
        return pm.executeQuery();
    }

    public boolean Delete(String ID) throws Exception {
        String sql = "Delete from DANGNHAP where BINARY_CHECKSUM(ID) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(sql);
        pm.setString(1, ID);
        return pm.executeUpdate() > 0;
    }

    public boolean Update(QuanLyTaiKhoan tk) throws Exception {
        String Sql = "Update DANGNHAP SET ID=?,MATKHAU=?,MANV=? WHERE ID=?";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, tk.getId());
        pm.setString(2, tk.getPASS());
        pm.setString(3, tk.getMANV());
        pm.setString(4, tk.getId());
        return pm.executeUpdate() > 0;
    }

    public QuanLyTaiKhoan Search(String ID) throws Exception {
        String Sql = "Select * from DANGNHAP Where BINARY_CHECKSUM(ID) = BINARY_CHECKSUM (?)";
        Connection conn = DatabaseConnection.openConnection();
        PreparedStatement pm = conn.prepareStatement(Sql);
        pm.setString(1, ID);
        ResultSet rs = pm.executeQuery();
        if (rs.next()) {
            QuanLyTaiKhoan tk = new QuanLyTaiKhoan();
            tk.setId(rs.getString("ID"));
            tk.setPASS(rs.getString("MATKHAU"));
            tk.setMANV(rs.getString("MANV"));
            return tk;
        }
        return null;
    }
}
