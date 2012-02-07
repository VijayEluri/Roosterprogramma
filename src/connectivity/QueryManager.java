package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Employee;
import model.Settings;
import model.WorkHours;
import roosterprogramma.RoosterProgramma;
import roosterprogramma.Utils;

/**
 *
 * @author UDP
 */
public class QueryManager {

    private final Dbmanager dbManager;

    /**
     * Sla het Dbmanager object op voor intern (private) gebruik
     *
     * @param dbmanager
     */
    public QueryManager(Dbmanager dbmanager) {
        this.dbManager = dbmanager;
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        String sql = "SELECT * FROM medewerkers;";
        try {
            ResultSet result = dbManager.doQuery(sql);
            while (result.next()) {
                employees.add(
                        new Employee(
                        result.getInt("personeelsnummer"),
                        result.getString("voornaam"),
                        result.getString("tussenvoegsel"),
                        result.getString("achternaam"),
                        result.getString("wachtwoord"),
                        result.getBoolean("fulltime"),
                        result.getBoolean("parttime"),
                        result.getBoolean("oproepkracht"),
                        result.getBoolean("baliemedewerker"),
                        result.getBoolean("museumdocent"),
                        result.getDouble("contracturen"),
                        result.getDouble("vakantiepercentage"),
                        result.getBoolean("admin"),
                        result.getBoolean("werkmaandag"),
                        result.getBoolean("werkdinsdag"),
                        result.getBoolean("werkwoensdag"),
                        result.getBoolean("werkdonderdag"),
                        result.getBoolean("werkvrijdag"),
                        result.getBoolean("werkzaterdag"),
                        result.getBoolean("werkzondag")));
            }
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, kan geen enkele gebruiker ophalen uit de database.", "Fout!", ex.getMessage(), false);
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        int fulltime = Utils.booleanToInt(employee.isFullTime());
        int parttime = Utils.booleanToInt(employee.isPartTime());
        int callworker = Utils.booleanToInt(employee.isCallWorker());
        int clerk = Utils.booleanToInt(employee.isClerk());
        int museumeducator = Utils.booleanToInt(employee.isMuseumEducator());
        int workmonday = Utils.booleanToInt(employee.isWorkMonday());
        int worktuesday = Utils.booleanToInt(employee.isWorkTuesday());
        int workwednesday = Utils.booleanToInt(employee.isWorkWednesday());
        int workthursday = Utils.booleanToInt(employee.isWorkThursday());
        int workfriday = Utils.booleanToInt(employee.isWorkFriday());
        int worksaturday = Utils.booleanToInt(employee.isWorkSaturday());
        int worksunday = Utils.booleanToInt(employee.isWorkSunday());
        String sql = "INSERT INTO medewerkers (personeelsnummer, voornaam, tussenvoegsel, achternaam, "
                + "wachtwoord, fulltime, parttime, oproepkracht, "
                + "baliemedewerker, museumdocent, contracturen, vakantiepercentage, "
                + "admin, werkmaandag, werkdinsdag, werkwoensdag, werkdonderdag, werkvrijdag, werkzaterdag, werkzondag) "
                + "VALUES('" + employee.getEmployeeNumber() + "', '" + employee.getFirstName().replace("'", "\'") + "', '"
                + employee.getInsertion().replace("'", "\'") + "', '" + employee.getFamilyName().replace("'", "\'") + "', '"
                + employee.getPassword() + "', '" + fulltime + "', '" + parttime + "', '" + callworker + "', '" + clerk + "', '"
                + museumeducator + "', '" + employee.getContractHours() + "', '" + employee.getVacationPercentage() + "', '0', '" + workmonday + "', '"
                + worktuesday + "', '" + workwednesday + "', '" + workthursday + "', '" + workfriday + "', '" + worksaturday + "', '" + worksunday + "')";
        try {
            dbManager.insertQuery(sql);
            RoosterProgramma.getInstance().addEmployee(employee);
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, nieuwe medewerker kon niet worden toegevoegd.", "Fout!", ex.getMessage(), false);
        }
    }

    public void changeEmployee(Employee employee) {
        int workmonday = Utils.booleanToInt(employee.isWorkMonday());
        int worktuesday = Utils.booleanToInt(employee.isWorkTuesday());
        int workwednesday = Utils.booleanToInt(employee.isWorkWednesday());
        int workthursday = Utils.booleanToInt(employee.isWorkThursday());
        int workfriday = Utils.booleanToInt(employee.isWorkFriday());
        int worksaturday = Utils.booleanToInt(employee.isWorkSaturday());
        int worksunday = Utils.booleanToInt(employee.isWorkSunday());
        String sql = "UPDATE medewerkers SET "
                + "voornaam = '" + employee.getFirstName().replace("'", "\'") + "', "
                + "tussenvoegsel = '" + employee.getInsertion().replace("'", "\'") + "', "
                + "achternaam = '" + employee.getFamilyName().replace("'", "\'") + "', "
                + "wachtwoord = '" + employee.getPassword().replace("'", "\'") + "', "
                + "fulltime = '" + Utils.booleanToInt(employee.isFullTime()) + "', "
                + "parttime = '" + Utils.booleanToInt(employee.isPartTime()) + "', "
                + "oproepkracht = '" + Utils.booleanToInt(employee.isCallWorker()) + "', "
                + "baliemedewerker = '" + Utils.booleanToInt(employee.isClerk()) + "', "
                + "museumdocent = '" + Utils.booleanToInt(employee.isMuseumEducator()) + "', "
                + "contracturen = '" + employee.getContractHours() + "', "
                + "vakantiepercentage = '" + employee.getVacationPercentage() + "', "
                + "admin = '" + Utils.booleanToInt(employee.isAdmin()) + "', "
                + "werkmaandag = '" + workmonday + "', "
                + "werkdinsdag = '" + worktuesday + "', "
                + "werkwoensdag = '" + workwednesday + "', "
                + "werkdonderdag = '" + workthursday + "', "
                + "werkvrijdag = '" + workfriday + "', "
                + "werkzaterdag = '" + worksaturday + "', "
                + "werkzondag = '" + worksunday + "' "
                + "WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "';";
        try {
            dbManager.insertQuery(sql);
            RoosterProgramma.getInstance().changeEmployee(employee);
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, medewerker kon niet worden gewijzigd.", "Fout!", ex.getMessage(), false);
        }
    }

    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM medewerkers WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "';";
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, medewerker kon niet worden verwijderd.", "Fout!", ex.getMessage(), false);
        }
    }

    public WorkHours getWorkHours(int employeeNumber, String date) {
        WorkHours hours = new WorkHours();
        String sql = "SELECT * FROM werktijden WHERE personeelsnummer = '" + employeeNumber + "' AND datum = '" + date + "';";
        try {
            ResultSet result = dbManager.doQuery(sql);
            if (result.next()) {
                hours = new WorkHours(
                        employeeNumber, result.getString("datum"), result.getString("ingeroosterd"),
                        result.getDouble("gewerkt"), result.getDouble("compensatie150"), result.getDouble("compensatie200"),
                        result.getDouble("vakantie"), result.getDouble("adv"), result.getDouble("ziekte"),
                        result.getDouble("verlof"), result.getDouble("opgcompensatie"), result.getString("opmerking"));
            }
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, kon niet de werkuren van personeelsnummer '" + employeeNumber + "' ophalen.", "Fout!", ex.getMessage(), false);
        }
        return hours;
    }

    public void insertWorkHours(WorkHours hour) {
        String sql = "INSERT INTO werktijden VALUES ('"
                + hour.getEmployeeNumber()
                + "', '" + hour.getDate()
                + "', '" + hour.getShouldWork()
                + "', '" + hour.getWorked()
                + "', '" + hour.getCompensation150()
                + "', '" + hour.getCompensation200()
                + "', '" + hour.getVacation()
                + "', '" + hour.getADV()
                + "', '" + hour.getIllness()
                + "', '" + hour.getLeave()
                + "', '" + hour.getWithdrawnCompensation()
                + "', '" + hour.getComment()
                + "');";
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, het opslaan van de gewerkte uren is niet gelukt.", "Fout!", ex.getMessage(), false);
        }
    }

    public boolean updateWorkHours(WorkHours hour) {
        String sql = "UPDATE werktijden SET "
                + "ingeroosterd = '" + hour.getShouldWork() + "', "
                + "gewerkt = '" + hour.getWorked() + "', "
                + "compensatie150 = '" + hour.getCompensation150() + "', "
                + "compensatie200 = '" + hour.getCompensation200() + "', "
                + "vakantie = '" + hour.getVacation() + "', "
                + "adv = '" + hour.getADV() + "', "
                + "ziekte = '" + hour.getIllness() + "', "
                + "verlof = '" + hour.getLeave() + "', "
                + "opgcompensatie = '" + hour.getWithdrawnCompensation() + "', "
                + "opmerking = '" + hour.getComment() + "' "
                + "WHERE personeelsnummer = '" + hour.getEmployeeNumber() + "' "
                + "AND datum = '" + hour.getDate()
                + "';";
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, het opslaan van de gewerkte uren is niet gelukt.", "Fout!", ex.getMessage(), false);
            return false;
        }
        return true;
    }

    public Settings getSettings() {
        Settings settings = new Settings();
        String sql = "SELECT * FROM settings;";
        try {
            ResultSet result = dbManager.doQuery(sql);
            if (result.next()) {
                settings.setSettings(
                        result.getString("x1"),
                        result.getString("x2"),
                        result.getString("x3"));
            }
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, het ophalen van de instellingen is niet gelukt.", "Fout!", ex.getMessage(), false);
        }
        return settings;
    }

    public void saveSettings(Settings settings) {
        String sql = "UPDATE settings SET "
                + "x1 = '" + settings.getX1() + "', "
                + "x2 = '" + settings.getX2() + "', "
                + "x3 = '" + settings.getX3() + "';";
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, het opslaan van de instellingen is niet gelukt.", "Fout!", ex.getMessage(), false);
        }
    }

    public void addToLog(String message) {
        String sql = "INSERT INTO `log` (`message`) VALUES ('" + message + "');";
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            Utils.showMessage("ERNSTIGE FOUT, er is een fout opgetreden bij het loggen van een fout.", "Fout!", ex.getMessage(), true);
        }
    }
}