/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import roosterprogramma.RoosterProgramma;

/**
 *
 * @author UDP
 */
public class Employee {

    private int employeeNumber;
    private String firstName;
    private String familyName;
    private String password;
    private boolean fullTime;
    private boolean partTime;
    private boolean callWorker;
    private boolean admin;

    public Employee() {
        this(0, "", "", "", false, false, false, false);
    }

    public Employee(int personeelsNummer, String voornaam,  String achternaam, String wachtwoord,
            boolean fulltime, boolean parttime, boolean oproepkracht, boolean admin) {
        this.employeeNumber = personeelsNummer;
        this.firstName = voornaam;
        this.familyName = achternaam;
        this.password = wachtwoord;
        this.fullTime = fulltime;
        this.partTime = parttime;
        this.callWorker = oproepkracht;
        this.admin = admin;
    }

    public void delete() {
        RoosterProgramma.getQueryManager().deleteEmployee(getEmployeeNumber());
    }

    /**
     * @return the employeeNumber
     */
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * @param employeeNumber the employeeNumber to set
     */
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * @param familyName the familyName to set
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullTime
     */
    public boolean isFullTime() {
        return fullTime;
    }

    /**
     * @param fullTime the fullTime to set
     */
    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    /**
     * @return the partTime
     */
    public boolean isPartTime() {
        return partTime;
    }

    /**
     * @param partTime the partTime to set
     */
    public void setPartTime(boolean partTime) {
        this.partTime = partTime;
    }

    /**
     * @return the callWorker
     */
    public boolean isCallWorker() {
        return callWorker;
    }

    /**
     * @param callWorker the callWorker to set
     */
    public void setCallWorker(boolean callWorker) {
        this.callWorker = callWorker;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }
}