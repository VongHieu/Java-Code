/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Model;

import java.util.Date;

/**
 *
 * @author vongh
 */
public class NhanVien {
    private String MANV,TENNV,NgaySinh,GIOITINH,DIACHI,EMAIL,DIENTHOAI,MaPB,MAPHUCAP,MaCV,MaThue;
    int NGUOIPHUTHUOC;
    float LUONGCOBAN;

    public NhanVien() {
    }

    public NhanVien(String MANV, String TENNV, String NgaySinh, String GIOITINH, String DIACHI, String EMAIL, String DIENTHOAI, String MaPB, String MAPHUCAP, String MaCV, String MaThue, int NGUOIPHUTHUOC, float LUONGCOBAN) {
        this.MANV = MANV;
        this.TENNV = TENNV;
        this.NgaySinh = NgaySinh;
        this.GIOITINH = GIOITINH;
        this.DIACHI = DIACHI;
        this.EMAIL = EMAIL;
        this.DIENTHOAI = DIENTHOAI;
        this.MaPB = MaPB;
        this.MAPHUCAP = MAPHUCAP;
        this.MaCV = MaCV;
        this.MaThue = MaThue;
        this.NGUOIPHUTHUOC = NGUOIPHUTHUOC;
        this.LUONGCOBAN = LUONGCOBAN;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getTENNV() {
        return TENNV;
    }

    public void setTENNV(String TENNV) {
        this.TENNV = TENNV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(String GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDIENTHOAI() {
        return DIENTHOAI;
    }

    public void setDIENTHOAI(String DIENTHOAI) {
        this.DIENTHOAI = DIENTHOAI;
    }

    public String getMaPB() {
        return MaPB;
    }

    public void setMaPB(String MaPB) {
        this.MaPB = MaPB;
    }

    public String getMAPHUCAP() {
        return MAPHUCAP;
    }

    public void setMAPHUCAP(String MAPHUCAP) {
        this.MAPHUCAP = MAPHUCAP;
    }

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }

    public String getMaThue() {
        return MaThue;
    }

    public void setMaThue(String MaThue) {
        this.MaThue = MaThue;
    }

    public int getNGUOIPHUTHUOC() {
        return NGUOIPHUTHUOC;
    }

    public void setNGUOIPHUTHUOC(int NGUOIPHUTHUOC) {
        this.NGUOIPHUTHUOC = NGUOIPHUTHUOC;
    }

    public float getLUONGCOBAN() {
        return LUONGCOBAN;
    }

    public void setLUONGCOBAN(float LUONGCOBAN) {
        this.LUONGCOBAN = LUONGCOBAN;
    }

    

    
}
