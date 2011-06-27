/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
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
    private boolean admin;
    private List<WorkHours> workHours = new ArrayList<WorkHours>();

    public Employee() {
        this(0, "", "", "", "", true, false, false, false, false, 0.0, false);
    }

    public Employee(int personeelsNummer, String voornaam, String tussenvoegsel, String achternaam, String wachtwoord,
            boolean fulltime, boolean parttime, boolean oproepkracht, boolean baliemedewerker, boolean museumdocent, double contracturen, boolean admin) {
        if (tussenvoegsel == null) {
            tussenvoegsel = "";
        }
        this.employeeNumber = personeelsNummer;
        this.firstName = voornaam;
        this.insertion = tussenvoegsel;
        this.familyName = achternaam;
        this.password = wachtwoord;
        this.fullTime = fulltime;
        this.partTime = parttime;
        this.callWorker = oproepkracht;
        this.clerk = baliemedewerker;
        this.museumEducator = museumdocent;
        this.contractHours = contracturen;
        this.admin = admin;
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
        if (!RoosterProgramma.getInstance().isEmpty(insertion)) {
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

    public WorkHours getWorkHour(String date) {
        WorkHours workHour = new WorkHours();
        workHour.setEmployee(this);
        workHour.setDate(date);
        if (workHours.isEmpty()) {
            workHours = RoosterProgramma.getQueryManager().getWorkHours(this);
        }
        for (WorkHours hour : workHours) {
            if (hour.getDate().equals(date)) {
                workHour = hour;
            }
        }
        return workHour;
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
}