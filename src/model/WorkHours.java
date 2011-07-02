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
public class WorkHours {

    private String date;
    private String shouldWork;
    private double worked;
    private double compensation150;
    private double compensation200;
    private double vacation;
    private double ADV;
    private double illness;
    private double leave;
    private double project;
    private Employee employee;

    public WorkHours() {
        this(new Employee(), "", "", 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public WorkHours(Employee employee, String date, String shouldWork, double worked,
            double compensation150, double compensation200, double vacation, double ADV,
            double illness, double leave, double project) {
        this.employee = employee;
        this.date = date;
        this.shouldWork = shouldWork;
        this.worked = worked;
        this.compensation150 = compensation150;
        this.compensation200 = compensation200;
        this.vacation = vacation;
        this.ADV = ADV;
        this.illness = illness;
        this.leave = leave;
        this.project = project;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the worked
     */
    public double getWorked() {
        return worked;
    }

    /**
     * @param worked the worked to set
     */
    public void setWorked(double worked) {
        this.worked = worked;
    }

    /**
     * @return the compensation150
     */
    public double getCompensation150() {
        return compensation150;
    }

    /**
     * @param compensation150 the compensation150 to set
     */
    public void setCompensation150(double compensation150) {
        this.compensation150 = compensation150;
    }

    /**
     * @return the compensation200
     */
    public double getCompensation200() {
        return compensation200;
    }

    /**
     * @param compensation200 the compensation200 to set
     */
    public void setCompensation200(double compensation200) {
        this.compensation200 = compensation200;
    }

    /**
     * @return the vacation
     */
    public double getVacation() {
        return vacation;
    }

    /**
     * @param vacation the vacation to set
     */
    public void setVacation(double vacation) {
        this.vacation = vacation;
    }

    /**
     * @return the ADV
     */
    public double getADV() {
        return ADV;
    }

    /**
     * @param ADV the ADV to set
     */
    public void setADV(double ADV) {
        this.ADV = ADV;
    }

    /**
     * @return the illness
     */
    public double getIllness() {
        return illness;
    }

    /**
     * @param illness the illness to set
     */
    public void setIllness(double illness) {
        this.illness = illness;
    }

    /**
     * @return the verlof
     */
    public double getLeave() {
        return leave;
    }

    /**
     * @param leave the verlof to set
     */
    public void setLeave(double leave) {
        this.leave = leave;
    }

    /**
     * @return the project
     */
    public double getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(double project) {
        this.project = project;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the shouldwork
     */
    public String getShouldWork() {
        return shouldWork;
    }

    /**
     * @param shouldWork the shouldwork to set
     */
    public void setShouldWork(String shouldWork) {
        this.shouldWork = shouldWork;
    }
    
    public void update() {
        RoosterProgramma.getQueryManager().updateWorkHours(this);
    }
}
