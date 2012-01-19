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

    public String getX1() {
        return x1Start + "-" + x1End;
    }

    public String getX2() {
        return x2Start + "-" + x2End;
    }

    public String getX3() {
        return x3Start + "-" + x3End;
    }

    public double getX1Duration() {
        int startMinutes = (Integer.parseInt(getX1Start().substring(0, 2)) * 60) + Integer.parseInt(getX1Start().substring(2, 4));
        int endMinutes = (Integer.parseInt(getX1End().substring(0, 2)) * 60) + Integer.parseInt(getX1End().substring(2, 4));
        return (endMinutes - startMinutes) / 60;
    }

    public double getX2Duration() {
        int startMinutes = (Integer.parseInt(getX2Start().substring(0, 2)) * 60) + Integer.parseInt(getX2Start().substring(2, 4));
        int endMinutes = (Integer.parseInt(getX2End().substring(0, 2)) * 60) + Integer.parseInt(getX2End().substring(2, 4));
        return (endMinutes - startMinutes) / 60;
    }

    public double getX3Duration() {
        int startMinutes = (Integer.parseInt(getX3Start().substring(0, 2)) * 60) + Integer.parseInt(getX3Start().substring(2, 4));
        int endMinutes = (Integer.parseInt(getX3End().substring(0, 2)) * 60) + Integer.parseInt(getX3End().substring(2, 4));
        return (endMinutes - startMinutes) / 60;
    }
}
