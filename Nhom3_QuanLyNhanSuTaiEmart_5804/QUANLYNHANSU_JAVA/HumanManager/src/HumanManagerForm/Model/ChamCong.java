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
public class ChamCong {

    String MABCC, MANV, HOTENNV, TENCV, MAPB;
    int THANG, NAM, SONGAYLAM, SONGAYPHEP, SONGAYKHONGPHEP;

    public ChamCong() {
    }

    public ChamCong(String MABCC, String MANV, String HOTENNV, String TENCV, String MAPB, int THANG, int NAM, int SONGAYLAM, int SONGAYPHEP, int SONGAYKHONGPHEP) {
        this.MABCC = MABCC;
        this.MANV = MANV;
        this.HOTENNV = HOTENNV;
        this.TENCV = TENCV;
        this.MAPB = MAPB;
        this.THANG = THANG;
        this.NAM = NAM;
        this.SONGAYLAM = SONGAYLAM;
        this.SONGAYPHEP = SONGAYPHEP;
        this.SONGAYKHONGPHEP = SONGAYKHONGPHEP;
    }

    public String getMANV() {
        return MANV;
    }

    public String getMABCC() {
        return MABCC;
    }

    public void setMABCC(String MABCC) {
        this.MABCC = MABCC;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getHOTENNV() {
        return HOTENNV;
    }

    public void setHOTENNV(String HOTENNV) {
        this.HOTENNV = HOTENNV;
    }

    public String getTENCV() {
        return TENCV;
    }

    public void setTENCV(String TENCV) {
        this.TENCV = TENCV;
    }

    public String getMAPB() {
        return MAPB;
    }

    public void setMAPB(String MAPB) {
        this.MAPB = MAPB;
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

    public int getSONGAYLAM() {
        return SONGAYLAM;
    }

    public void setSONGAYLAM(int SONGAYLAM) {
        this.SONGAYLAM = SONGAYLAM;
    }

    public int getSONGAYPHEP() {
        return SONGAYPHEP;
    }

    public void setSONGAYPHEP(int SONGAYPHEP) {
        this.SONGAYPHEP = SONGAYPHEP;
    }

    public int getSONGAYKHONGPHEP() {
        return SONGAYKHONGPHEP;
    }

    public void setSONGAYKHONGPHEP(int SONGAYKHONGPHEP) {
        this.SONGAYKHONGPHEP = SONGAYKHONGPHEP;
    }

}
