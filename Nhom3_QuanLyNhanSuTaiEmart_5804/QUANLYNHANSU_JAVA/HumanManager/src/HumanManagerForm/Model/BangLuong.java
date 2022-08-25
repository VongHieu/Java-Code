/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Model;

/**
 *
 * @author vongh
 */
public class BangLuong {
    String MABL,MANV,TENNV,CHUCVU,NGAYNHAN;
    int THANG,NAM,SONGAYCONG;
    float MucluongCB,TongPC,TienBH,SoTienThue,TamUng,LuongTH;

    public BangLuong() {
    }

    public BangLuong(String MABL, String MANV, String TENNV, String CHUCVU, String NGAYNHAN, int THANG, int NAM, int SONGAYCONG, float MucluongCB, float TongPC, float TienBH, float SoTienThue, float TamUng, float LuongTH) {
        this.MABL = MABL;
        this.MANV = MANV;
        this.TENNV = TENNV;
        this.CHUCVU = CHUCVU;
        this.NGAYNHAN = NGAYNHAN;
        this.THANG = THANG;
        this.NAM = NAM;
        this.SONGAYCONG = SONGAYCONG;
        this.MucluongCB = MucluongCB;
        this.TongPC = TongPC;
        this.TienBH = TienBH;
        this.SoTienThue = SoTienThue;
        this.TamUng = TamUng;
        this.LuongTH = LuongTH;
    }

    public String getMABL() {
        return MABL;
    }

    public void setMABL(String MABL) {
        this.MABL = MABL;
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

    public String getCHUCVU() {
        return CHUCVU;
    }

    public void setCHUCVU(String CHUCVU) {
        this.CHUCVU = CHUCVU;
    }

    public String getNGAYNHAN() {
        return NGAYNHAN;
    }

    public void setNGAYNHAN(String NGAYNHAN) {
        this.NGAYNHAN = NGAYNHAN;
    }

    public int getTHANG() {
        return THANG;
    }

    public void setTHANG(int THANG) {
        this.THANG = THANG;
    }

    public int getNAM() {
        return NAM;
    }

    public void setNAM(int NAM) {
        this.NAM = NAM;
    }

    public int getSONGAYCONG() {
        return SONGAYCONG;
    }

    public void setSONGAYCONG(int SONGAYCONG) {
        this.SONGAYCONG = SONGAYCONG;
    }

    public float getMucluongCB() {
        return MucluongCB;
    }

    public void setMucluongCB(float MucluongCB) {
        this.MucluongCB = MucluongCB;
    }

    public float getTongPC() {
        return TongPC;
    }

    public void setTongPC(float TongPC) {
        this.TongPC = TongPC;
    }

    public float getTienBH() {
        return TienBH;
    }

    public void setTienBH(float TienBH) {
        this.TienBH = TienBH;
    }

    public float getSoTienThue() {
        return SoTienThue;
    }

    public void setSoTienThue(float SoTienThue) {
        this.SoTienThue = SoTienThue;
    }

    public float getTamUng() {
        return TamUng;
    }

    public void setTamUng(float TamUng) {
        this.TamUng = TamUng;
    }

    public float getLuongTH() {
        return LuongTH;
    }

    public void setLuongTH(float LuongTH) {
        this.LuongTH = LuongTH;
    }
    
    
}
