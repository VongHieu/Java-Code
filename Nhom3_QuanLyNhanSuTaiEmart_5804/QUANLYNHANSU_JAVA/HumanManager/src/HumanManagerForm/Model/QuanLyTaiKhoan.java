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
public class QuanLyTaiKhoan {
    private String Id,PASS,MANV;

    public QuanLyTaiKhoan() {
    }

    public QuanLyTaiKhoan(String Id, String PASS, String MANV) {
        this.Id = Id;
        this.PASS = PASS;
        this.MANV = MANV;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }
    
    
}
