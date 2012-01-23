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
    private String insertion;
    private String familyName;
    private String password;
    private boolean fullTime;
    private boolean partTime;
    private boolean callWorker;
    private boolean clerk;
    private boolean museumEducator;
    private double contractHours;
    private double vacationPercentage;
    private boolean admin;
    private boolean workMonday, workTuesday, workWednesday, workThursday, workFriday, workSaturday, workSunday;

    public Employee() {
        this(0, "", "", "", "", true, false, false, false, false, 0.0, 0.0, false, false, false, false, false, false, false, false);
    }

    public Employee(int personeelsNummer, String voornaam, String tussenvoegsel, String achternaam, String wachtwoord,
            boolean fulltime, boolean parttime, boolean oproepkracht, boolean baliemedewerker,
            boolean museumdocent, double contracturen, double vacationPercentage, boolean admin, boolean workMonday, boolean workTuesday,
            boolean workWednesday, boolean workThursday, boolean workFriday, boolean workSaturday, boolean workSunday) {
        this.employeeNumber = personeelsNummer;
        this.firstName = voornaam;
        this.insertion = tussenvoegsel == null ? "" : tussenvoegsel;
        this.familyName = achternaam;
        this.password = wachtwoord;
        this.fullTime = fulltime;
        this.partTime = parttime;
        this.callWorker = oproepkracht;
        this.clerk = baliemedewerker;
        this.museumEducator = museumdocent;
        this.contractHours = contracturen;
        this.vacationPercentage = vacationPercentage;
        this.admin = admin;
        this.workMonday = workMonday;
        this.workTuesday = workTuesday;
        this.workWednesday = workWednesday;
        this.workThursday = workThursday;
        this.workFriday = workFriday;
        this.workSaturday = workSaturday;
        this.workSunday = workSunday;
    }

    public void delete() {
        RoosterProgramma.getQueryManager().deleteEmployee(this);
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

    public String getFullName() {
        String name = firstName + " ";
        if (!insertion.isEmpty()) {
            name += insertion + " ";
        }
        name += familyName;
        return name;
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

    /**
     * @return the contractHours
     */
    public double getContractHours() {
        return contractHours;
    }

    /**
     * @param contractHours the contractHours to set
     */
    public void setContractHours(double contractHours) {
        this.contractHours = contractHours;
    }

    /**
     * @return the clerk
     */
    public boolean isClerk() {
        return clerk;
    }

    /**
     * @param clerk the clerk to set
     */
    public void setClerk(boolean clerk) {
        this.clerk = clerk;
    }

    /**
     * @return the museumEducator
     */
    public boolean isMuseumEducator() {
        return museumEducator;
    }

    /**
     * @param museumEducator the museumEducator to set
     */
    public void setMuseumEducator(boolean museumEducator) {
        this.museumEducator = museumEducator;
    }

    public void update() {
        RoosterProgramma.getQueryManager().changeEmployee(this);
    }

    /**
     * @return the insertion
     */
    public String getInsertion() {
        return insertion;
    }

    /**
     * @param insertion the insertion to set
     */
    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    /**
     * @return the vacationPercentage
     */
    public double getVacationPercentage() {
        return vacationPercentage;
    }

    /**
     * @param vacationPercentage the vacationPercentage to set
     */
    public void setVacationPercentage(double vacationPercentage) {
        this.vacationPercentage = vacationPercentage;
    }

    /**
     * @return the workMonday
     */
    public boolean isWorkMonday() {
        return workMonday;
    }

    /**
     * @param workMonday the workMonday to set
     */
    public void setWorkMonday(boolean workMonday) {
        this.workMonday = workMonday;
    }

    /**
     * @return the workTuesday
     */
    public boolean isWorkTuesday() {
        return workTuesday;
    }

    /**
     * @param workTuesday the workTuesday to set
     */
    public void setWorkTuesday(boolean workTuesday) {
        this.workTuesday = workTuesday;
    }

    /**
     * @return the workWednesday
     */
    public boolean isWorkWednesday() {
        return workWednesday;
    }

    /**
     * @param workWednesday the workWednesday to set
     */
    public void setWorkWednesday(boolean workWednesday) {
        this.workWednesday = workWednesday;
    }

    /**
     * @return the workThursday
     */
    public boolean isWorkThursday() {
        return workThursday;
    }

    /**
     * @param workThursday the workThursday to set
     */
    public void setWorkThursday(boolean workThursday) {
        this.workThursday = workThursday;
    }

    /**
     * @return the workFriday
     */
    public boolean isWorkFriday() {
        return workFriday;
    }

    /**
     * @param workFriday the workFriday to set
     */
    public void setWorkFriday(boolean workFriday) {
        this.workFriday = workFriday;
    }

    /**
     * @return the workSaturday
     */
    public boolean isWorkSaturday() {
        return workSaturday;
    }

    /**
     * @param workSaturday the workSaturday to set
     */
    public void setWorkSaturday(boolean workSaturday) {
        this.workSaturday = workSaturday;
    }

    /**
     * @return the workSunday
     */
    public boolean isWorkSunday() {
        return workSunday;
    }

    /**
     * @param workSunday the workSunday to set
     */
    public void setWorkSunday(boolean workSunday) {
        this.workSunday = workSunday;
    }
}