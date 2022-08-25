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
public class BangCap {

    String MABC, MANV, TENBC, LOAIBC, NGAYCAP, DONVICAP;

    public BangCap() {
    }

    public BangCap(String MABC, String MANV, String TENBC, String LOAIBC, String NGAYCAP, String DONVICAP) {
        this.MABC = MABC;
        this.MANV = MANV;
        this.TENBC = TENBC;
        this.LOAIBC = LOAIBC;
        this.NGAYCAP = NGAYCAP;
        this.DONVICAP = DONVICAP;
    }

    public String getMABC() {
        return MABC;
    }

    public void setMABC(String MABC) {
        this.MABC = MABC;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getTENBC() {
        return TENBC;
    }

    public void setTENBC(String TENBC) {
        this.TENBC = TENBC;
    }

    public String getLOAIBC() {
        return LOAIBC;
    }

    public void setLOAIBC(String LOAIBC) {
        this.LOAIBC = LOAIBC;
    }

    public String getNGAYCAP() {
        return NGAYCAP;
    }

    public void setNGAYCAP(String NGAYCAP) {
        this.NGAYCAP = NGAYCAP;
    }

    public String getDONVICAP() {
        return DONVICAP;
    }

    public void setDONVICAP(String DONVICAP) {
        this.DONVICAP = DONVICAP;
    }

}
