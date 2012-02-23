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
    private contractTypes contractType;
    private employeeTypes employeeType;
    private double contractHours;
    private double vacationPercentage;
    private boolean admin;
    private boolean workMonday, workTuesday, workWednesday, workThursday, workFriday, workSaturday, workSunday;

    /**
     *
     */
    public enum contractTypes {

        /**
         *
         */
        FULLTIME,
        /**
         *
         */
        PARTTIME,
        /**
         *
         */
        CALLWORKER,
        /**
         *
         */
        VOLUNTEER;

        @Override
        public String toString() {
            return contractStrings[this.ordinal()];
        }
    }
    private static final String[] contractStrings = {"Fulltime", "Parttime", "Oproepkracht", "Vrijwilliger"};

    /**
     *
     */
    public enum employeeTypes {

        /**
         *
         */
        CLERK,
        /**
         *
         */
        MUSEUMEDUCATOR,
        /**
         *
         */
        OTHER,
        /**
         *
         */
        CPH,
        /**
         *
         */
        KUIPERIJ;

        @Override
        public String toString() {
            return employeeStrings[this.ordinal()];
        }
    }
    private static final String[] employeeStrings = {"Baliemedewerker", "Museumdocent", "Anders", "CPH", "Kuiperij"};

    /**
     *
     */
    public Employee() {
        this(0, "", "", "", "", contractTypes.CALLWORKER, employeeTypes.CLERK, 0.0, 0.0, false, false, false, false, false, false, false, false);
    }

    /**
     *
     * @param personeelsNummer
     * @param voornaam
     * @param tussenvoegsel
     * @param achternaam
     * @param wachtwoord
     * @param contractType
     * @param employeeType
     * @param contracturen
     * @param vacationPercentage
     * @param admin
     * @param workMonday
     * @param workTuesday
     * @param workWednesday
     * @param workThursday
     * @param workFriday
     * @param workSaturday
     * @param workSunday
     */
    public Employee(int personeelsNummer, String voornaam, String tussenvoegsel, String achternaam, String wachtwoord,
            contractTypes contractType, employeeTypes employeeType, double contracturen, double vacationPercentage, boolean admin, boolean workMonday, boolean workTuesday,
            boolean workWednesday, boolean workThursday, boolean workFriday, boolean workSaturday, boolean workSunday) {
        this.employeeNumber = personeelsNummer;
        this.firstName = voornaam;
        this.insertion = tussenvoegsel == null ? "" : tussenvoegsel;
        this.familyName = achternaam;
        this.password = wachtwoord;
        this.contractType = contractType;
        this.employeeType = employeeType;
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

    /**
     *
     */
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

    /**
     *
     * @return
     */
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
     * @param contractType the contract type to set
     */
    public void setContractType(contractTypes contractType) {
        this.contractType = contractType;
    }

    /**
     * @return the contractType
     */
    public contractTypes getContractType() {
        return this.contractType;
    }

    /**
     *
     * @return
     */
    public boolean isFullTime() {
        return this.contractType == contractTypes.FULLTIME;
    }

    /**
     *
     * @return
     */
    public boolean isPartTime() {
        return this.contractType == contractTypes.PARTTIME;
    }

    /**
     *
     * @return
     */
    public boolean isCallWorker() {
        return this.contractType == contractTypes.CALLWORKER;
    }

    /**
     *
     * @return
     */
    public boolean isVolunteer() {
        return this.contractType == contractTypes.VOLUNTEER;
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
     * @param employeeType the contract type to set
     */
    public void setEmployeeType(employeeTypes employeeType) {
        this.employeeType = employeeType;
    }

    /**
     * @return the contractType
     */
    public employeeTypes getEmployeeType() {
        return this.employeeType;
    }

    /**
     * @return the clerk
     */
    public boolean isClerk() {
        return this.employeeType == employeeTypes.CLERK;
    }

    /**
     * @return the museumEducator
     */
    public boolean isMuseumEducator() {
        return this.employeeType == employeeTypes.MUSEUMEDUCATOR;
    }

    /**
     *
     */
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