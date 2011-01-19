/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Dark
 */
public class EmployeeInfo {

    private int contracturen, tewerken, werkelijkgewerkt, gewerkttebetalen,
        gewerkttecompenseren, mindergewerkt, atv, vakantie, compensatie33, compensatie50,
        compensatie100, dokter, ziekte, eigenrekening, feestdagen, compensatieopname, caoverlof;

    public EmployeeInfo() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public EmployeeInfo(int contracturen, int tewerken, int werkelijkgewerkt, int gewerkttebetalen,
        int gewerkttecompenseren, int mindergewerkt, int atv, int vakantie, int compensatie33, int compensatie50,
        int compensatie100, int dokter, int ziekte, int eigenrekening, int feestdagen, int compensatieopname, int caoverlof)
    {
        this.contracturen = contracturen;
        this.tewerken = tewerken;
        this.werkelijkgewerkt = werkelijkgewerkt;
        this.gewerkttebetalen = gewerkttebetalen;
        this.gewerkttecompenseren = gewerkttecompenseren;
        this.mindergewerkt = mindergewerkt;
        this.atv = atv;
        this.vakantie = vakantie;
        this.compensatie33 = compensatie33;
        this.compensatie50 = compensatie50;
        this.compensatie100 = compensatie100;
        this.dokter = dokter;
        this.ziekte = ziekte;
        this.eigenrekening = eigenrekening;
        this.feestdagen = feestdagen;
        this.compensatieopname = compensatieopname;
        this.caoverlof = caoverlof;
    }

    /**
     * @return the contracturen
     */
    public int getContracturen() {
        return contracturen;
    }

    /**
     * @param contracturen the contracturen to set
     */
    public void setContracturen(int contracturen) {
        this.contracturen = contracturen;
    }

    /**
     * @return the tewerken
     */
    public int getTewerken() {
        return tewerken;
    }

    /**
     * @param tewerken the tewerken to set
     */
    public void setTewerken(int tewerken) {
        this.tewerken = tewerken;
    }

    /**
     * @return the werkelijkgewerkt
     */
    public int getWerkelijkgewerkt() {
        return werkelijkgewerkt;
    }

    /**
     * @param werkelijkgewerkt the werkelijkgewerkt to set
     */
    public void setWerkelijkgewerkt(int werkelijkgewerkt) {
        this.werkelijkgewerkt = werkelijkgewerkt;
    }

    /**
     * @return the gewerkttebetalen
     */
    public int getGewerkttebetalen() {
        return gewerkttebetalen;
    }

    /**
     * @param gewerkttebetalen the gewerkttebetalen to set
     */
    public void setGewerkttebetalen(int gewerkttebetalen) {
        this.gewerkttebetalen = gewerkttebetalen;
    }

    /**
     * @return the gewerkttecompenseren
     */
    public int getGewerkttecompenseren() {
        return gewerkttecompenseren;
    }

    /**
     * @param gewerkttecompenseren the gewerkttecompenseren to set
     */
    public void setGewerkttecompenseren(int gewerkttecompenseren) {
        this.gewerkttecompenseren = gewerkttecompenseren;
    }

    /**
     * @return the mindergewerkt
     */
    public int getMindergewerkt() {
        return mindergewerkt;
    }

    /**
     * @param mindergewerkt the mindergewerkt to set
     */
    public void setMindergewerkt(int mindergewerkt) {
        this.mindergewerkt = mindergewerkt;
    }

    /**
     * @return the atv
     */
    public int getAtv() {
        return atv;
    }

    /**
     * @param atv the atv to set
     */
    public void setAtv(int atv) {
        this.atv = atv;
    }

    /**
     * @return the vakantie
     */
    public int getVakantie() {
        return vakantie;
    }

    /**
     * @param vakantie the vakantie to set
     */
    public void setVakantie(int vakantie) {
        this.vakantie = vakantie;
    }

    /**
     * @return the compensatie33
     */
    public int getCompensatie33() {
        return compensatie33;
    }

    /**
     * @param compensatie33 the compensatie33 to set
     */
    public void setCompensatie33(int compensatie33) {
        this.compensatie33 = compensatie33;
    }

    /**
     * @return the compensatie50
     */
    public int getCompensatie50() {
        return compensatie50;
    }

    /**
     * @param compensatie50 the compensatie50 to set
     */
    public void setCompensatie50(int compensatie50) {
        this.compensatie50 = compensatie50;
    }

    /**
     * @return the compensatie100
     */
    public int getCompensatie100() {
        return compensatie100;
    }

    /**
     * @param compensatie100 the compensatie100 to set
     */
    public void setCompensatie100(int compensatie100) {
        this.compensatie100 = compensatie100;
    }

    /**
     * @return the dokter
     */
    public int getDokter() {
        return dokter;
    }

    /**
     * @param dokter the dokter to set
     */
    public void setDokter(int dokter) {
        this.dokter = dokter;
    }

    /**
     * @return the ziekte
     */
    public int getZiekte() {
        return ziekte;
    }

    /**
     * @param ziekte the ziekte to set
     */
    public void setZiekte(int ziekte) {
        this.ziekte = ziekte;
    }

    /**
     * @return the eigenrekening
     */
    public int getEigenrekening() {
        return eigenrekening;
    }

    /**
     * @param eigenrekening the eigenrekening to set
     */
    public void setEigenrekening(int eigenrekening) {
        this.eigenrekening = eigenrekening;
    }

    /**
     * @return the feestdagen
     */
    public int getFeestdagen() {
        return feestdagen;
    }

    /**
     * @param feestdagen the feestdagen to set
     */
    public void setFeestdagen(int feestdagen) {
        this.feestdagen = feestdagen;
    }

    /**
     * @return the compensatieopname
     */
    public int getCompensatieopname() {
        return compensatieopname;
    }

    /**
     * @param compensatieopname the compensatieopname to set
     */
    public void setCompensatieopname(int compensatieopname) {
        this.compensatieopname = compensatieopname;
    }

    /**
     * @return the caoverlof
     */
    public int getCaoverlof() {
        return caoverlof;
    }

    /**
     * @param caoverlof the caoverlof to set
     */
    public void setCaoverlof(int caoverlof) {
        this.caoverlof = caoverlof;
    }
}
