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
public class Thue {
    String MATHUE,MUCTHUE;
    float PHANTRAM;

    public Thue() {
    }

    public Thue(String MATHUE, String MUCTHUE, float PHANTRAM) {
        this.MATHUE = MATHUE;
        this.MUCTHUE = MUCTHUE;
        this.PHANTRAM = PHANTRAM;
    }

    public String getMATHUE() {
        return MATHUE;
    }

    public void setMATHUE(String MATHUE) {
        this.MATHUE = MATHUE;
    }

    public String getMUCTHUE() {
        return MUCTHUE;
    }

    public void setMUCTHUE(String MUCTHUE) {
        this.MUCTHUE = MUCTHUE;
    }

    public float getPHANTRAM() {
        return PHANTRAM;
    }

    public void setPHANTRAM(float PHANTRAM) {
        this.PHANTRAM = PHANTRAM;
    }
    
}
