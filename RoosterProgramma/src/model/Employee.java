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

    private int personeelsNummer;
    private String voornaam;
    private String achternaam;
    private String wachtwoord;
    private boolean fulltime;
    private boolean parttime;
    private boolean oproepkracht;
    private boolean admin;
    private EmployeeInfo maandag;
    private EmployeeInfo dinsdag;
    private EmployeeInfo woensdag;
    private EmployeeInfo donderdag;
    private EmployeeInfo vrijdag;
    private EmployeeInfo zaterdag;
    private EmployeeInfo zondag;

    public Employee() {
        this(0, "", "", "", false, false, false, false);
    }

    public Employee(int personeelsNummer, String voornaam,  String achternaam, String wachtwoord,
            boolean fulltime, boolean parttime, boolean oproepkracht, boolean admin) {
        this.personeelsNummer = personeelsNummer;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.wachtwoord = wachtwoord;
        this.fulltime = fulltime;
        this.parttime = parttime;
        this.oproepkracht = oproepkracht;
        this.admin = admin;
    }

    /**
     * @return the personeelsNummer
     */
    public int getPersoneelsNummer() {
        return personeelsNummer;
    }

    /**
     * @return the voornaam
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * @return the achternaam
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * @return the fulltime
     */
    public boolean isFulltime() {
        return fulltime;
    }

    /**
     * @return the parttime
     */
    public boolean isParttime() {
        return parttime;
    }

    /**
     * @return the noodhulp
     */
    public boolean isOproepkracht() {
        return oproepkracht;
    }

    /**
     * @return the wachtwoord
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * @param wachtwoord the wachtwoord to set
     */
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @param personeelsNummer the personeelsNummer to set
     */
    public void setPersoneelsNummer(int personeelsNummer) {
        this.personeelsNummer = personeelsNummer;
    }

    /**
     * @param voornaam the voornaam to set
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * @param achternaam the achternaam to set
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * @param fulltime the fulltime to set
     */
    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }

    /**
     * @param parttime the parttime to set
     */
    public void setParttime(boolean parttime) {
        this.parttime = parttime;
    }

    /**
     * @param oproepkracht the oproepkracht to set
     */
    public void setOproepkracht(boolean oproepkracht) {
        this.oproepkracht = oproepkracht;
    }

    /**
     * @return the maandag
     */
    public EmployeeInfo getMaandag() {
        return maandag;
    }

    /**
     * @param maandag the maandag to set
     */
    public void setMaandag(EmployeeInfo maandag) {
        this.maandag = maandag;
    }

    /**
     * @return the dinsdag
     */
    public EmployeeInfo getDinsdag() {
        return dinsdag;
    }

    /**
     * @param dinsdag the dinsdag to set
     */
    public void setDinsdag(EmployeeInfo dinsdag) {
        this.dinsdag = dinsdag;
    }

    /**
     * @return the woensdag
     */
    public EmployeeInfo getWoensdag() {
        return woensdag;
    }

    /**
     * @param woensdag the woensdag to set
     */
    public void setWoensdag(EmployeeInfo woensdag) {
        this.woensdag = woensdag;
    }

    /**
     * @return the donderdag
     */
    public EmployeeInfo getDonderdag() {
        return donderdag;
    }

    /**
     * @param donderdag the donderdag to set
     */
    public void setDonderdag(EmployeeInfo donderdag) {
        this.donderdag = donderdag;
    }

    /**
     * @return the vrijdag
     */
    public EmployeeInfo getVrijdag() {
        return vrijdag;
    }

    /**
     * @param vrijdag the vrijdag to set
     */
    public void setVrijdag(EmployeeInfo vrijdag) {
        this.vrijdag = vrijdag;
    }

    /**
     * @return the zaterdag
     */
    public EmployeeInfo getZaterdag() {
        return zaterdag;
    }

    /**
     * @param zaterdag the zaterdag to set
     */
    public void setZaterdag(EmployeeInfo zaterdag) {
        this.zaterdag = zaterdag;
    }

    /**
     * @return the zondag
     */
    public EmployeeInfo getZondag() {
        return zondag;
    }

    /**
     * @param zondag the zondag to set
     */
    public void setZondag(EmployeeInfo zondag) {
        this.zondag = zondag;
    }

    public void delete() {
        RoosterProgramma.getQueryManager().deleteEmployee(personeelsNummer);
    }
}