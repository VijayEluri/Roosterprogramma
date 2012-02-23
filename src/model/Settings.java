/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import roosterprogramma.RoosterProgramma;

/**
 *
 * @author Dark
 */
public class Settings {

    private String x1Start = "0000";
    private String x1End = "0000";
    private String x2Start = "0000";
    private String x2End = "0000";
    private String x3Start = "0000";
    private String x3End = "0000";

    /**
     *
     * @param x1
     * @param x2
     * @param x3
     */
    public void setSettings(String x1, String x2, String x3) {
        if (x1.length() == 9) { //  1130-1700 --> 9 karakters
            this.x1Start = x1.split("-")[0];
            this.x1End = x1.split("-")[1];
        }
        if (x2.length() == 9) {
            this.x2Start = x2.split("-")[0];
            this.x2End = x2.split("-")[1];
        }
        if (x3.length() == 9) {
            this.x3Start = x3.split("-")[0];
            this.x3End = x3.split("-")[1];
        }
    }

    /**
     *
     */
    public void save() {
        RoosterProgramma.getQueryManager().saveSettings(this);
    }

    /**
     * @return the x1Start
     */
    public String getX1Start() {
        return x1Start.replace(":", "");
    }

    /**
     * @return the x1Start
     */
    public String getX1End() {
        return x1End.replace(":", "");
    }

    /**
     * @return the x1Start
     */
    public String getX2Start() {
        return x2Start.replace(":", "");
    }

    /**
     * @return the x1Start
     */
    public String getX2End() {
        return x2End.replace(":", "");
    }

    /**
     * @return the x1Start
     */
    public String getX3Start() {
        return x3Start.replace(":", "");
    }

    /**
     * @return the x1Start
     */
    public String getX3End() {
        return x3End.replace(":", "");
    }

    /**
     *
     * @return
     */
    public String getX1() {
        return x1Start + "-" + x1End;
    }

    /**
     *
     * @return
     */
    public String getX2() {
        return x2Start + "-" + x2End;
    }

    /**
     *
     * @return
     */
    public String getX3() {
        return x3Start + "-" + x3End;
    }
}
