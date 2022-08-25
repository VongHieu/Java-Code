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
public class PhongBAn {
    String maPB,tenPB,SDT;

    public PhongBAn() {
    }

    public PhongBAn(String maPB, String tenPB, String SDT) {
        this.maPB = maPB;
        this.tenPB = tenPB;
        this.SDT = SDT;
    }

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    
}
