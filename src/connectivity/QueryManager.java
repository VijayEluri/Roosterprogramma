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
    private final static boolean DEBUG = false;

    /**
     * Sla het Dbmanager object op voor intern (private) gebruik
     *
     * @param dbmanager
     */
    public QueryManager(Dbmanager dbmanager) {
        this.dbManager = dbmanager;
    }

    /**
     *
     * @return an ArrayList of employees
     */
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        String sql = "SELECT * FROM medewerkers;";
        if (DEBUG) {
            System.err.println(sql);
        }
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
                        Utils.intToContractType(result.getInt("contracttype")),
                        Utils.intToEmployeeType(result.getInt("werknemerstype")),
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
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, kan geen enkele gebruiker ophalen uit de database.", "Fout!", ex.toString(), false);
        }
        return employees;
    }

    /**
     * Adds an employee to the database
     *
     * @param employee
     */
    public void addEmployee(Employee employee) {
        int workmonday = Utils.booleanToInt(employee.isWorkMonday());
        int worktuesday = Utils.booleanToInt(employee.isWorkTuesday());
        int workwednesday = Utils.booleanToInt(employee.isWorkWednesday());
        int workthursday = Utils.booleanToInt(employee.isWorkThursday());
        int workfriday = Utils.booleanToInt(employee.isWorkFriday());
        int worksaturday = Utils.booleanToInt(employee.isWorkSaturday());
        int worksunday = Utils.booleanToInt(employee.isWorkSunday());
        String sql = "INSERT INTO medewerkers (personeelsnummer, voornaam, tussenvoegsel, achternaam, "
                + "wachtwoord, contracttype, werknemerstype, contracturen, vakantiepercentage, "
                + "admin, werkmaandag, werkdinsdag, werkwoensdag, werkdonderdag, werkvrijdag, werkzaterdag, werkzondag) "
                + "VALUES('" + employee.getEmployeeNumber() + "', '" + employee.getFirstName().replace("'", "\'") + "', '"
                + employee.getInsertion().replace("'", "\'") + "', '" + employee.getFamilyName().replace("'", "\'") + "', '"
                + employee.getPassword() + "', '" + employee.getContractType().ordinal() + "', '" + employee.getEmployeeType().ordinal() + "', '"
                + employee.getContractHours() + "', '" + employee.getVacationPercentage() + "', '0', '" + workmonday + "', '"
                + worktuesday + "', '" + workwednesday + "', '" + workthursday + "', '" + workfriday + "', '" + worksaturday + "', '" + worksunday + "')";
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            dbManager.insertQuery(sql);
            RoosterProgramma.getInstance().addEmployee(employee);
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, nieuwe medewerker kon niet worden toegevoegd.", "Fout!", ex.toString(), false);
        }
    }

    /**
     * Changes an employee in the database
     *
     * @param employee
     */
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
                + "wachtwoord = '" + employee.getPassword() + "', "
                + "contracttype = '" + employee.getContractType().ordinal() + "', "
                + "werknemerstype = '" + employee.getEmployeeType().ordinal() + "', "
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
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            dbManager.executeUpdate(sql);
            Utils.showMessage("Succesvol gewijzigd.", "Gelukt!", "Account " + employee.getEmployeeNumber() + " is gewijzigd.", false);
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, medewerker kon niet worden gewijzigd.", "Fout!", ex.toString(), false);
        }
    }

    /**
     * Removes an employee from the database
     *
     * @param employee
     */
    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM medewerkers WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "';";
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            dbManager.insertQuery(sql);
            Utils.showMessage("Succesvol verwijderd.", "Gelukt!", "Account " + employee.getEmployeeNumber() + "is verwijderd.", false);
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, medewerker kon niet worden verwijderd.", "Fout!", ex.toString(), false);
        }
    }

    /**
     *
     * @param employee the employee who's workhours we want
     * @param date the date of which we want the workhours
     * @return WorkHours the hour justification of employee
     */
    public WorkHours getWorkHours(Employee employee, String date) {
        WorkHours hours = new WorkHours(-1, date);
        String sql = "SELECT * FROM werktijden WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "' AND datum = '" + date + "';";
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            ResultSet result = dbManager.doQuery(sql);
            if (result.next()) {
                hours = new WorkHours(
                        employee.getEmployeeNumber(), result.getString("datum"), result.getString("ingeroosterd"),
                        result.getDouble("gewerkt"), result.getDouble("compensatie150"), result.getDouble("compensatie200"),
                        result.getDouble("vakantie"), result.getDouble("adv"), result.getDouble("ziekte"),
                        result.getDouble("verlof"), result.getDouble("opgcompensatie"), result.getString("opmerking"));
            }
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, kon niet de werkuren van personeelsnummer '" + employee.getEmployeeNumber() + "' ophalen.", "Fout!", ex.toString(), false);
        }
        return hours;
    }

    /**
     *
     * @param hour
     * @return
     */
    public boolean insertWorkHours(WorkHours hour) {
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
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, het opslaan van de gewerkte uren is niet gelukt.", "Fout!", ex.toString(), false);
            return false;
        }
        return true;
    }

    /**
     *
     * @param hour
     * @return
     */
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
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            dbManager.executeUpdate(sql);
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, het opslaan van de gewerkte uren is niet gelukt.", "Fout!", ex.toString(), false);
            return false;
        }
        return true;
    }

    /**
     *
     * @param employeenumber
     * @param date
     * @return
     */
    public boolean deleteWorkHours(int employeenumber, String date) {
        String sql = "DELETE FROM werktijden WHERE personeelsnummer = '" + employeenumber + "' AND datum = '" + date + "';";
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            dbManager.executeUpdate(sql);
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, het weghalen van de te werken uren is mislukt.", "Fout!", ex.toString(), false);
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public Settings getSettings() {
        Settings settings = new Settings();
        String sql = "SELECT * FROM settings;";
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            ResultSet result = dbManager.doQuery(sql);
            if (result.next()) {
                settings.setSettings(
                        result.getString("x1"),
                        result.getString("x2"),
                        result.getString("x3"));
            }
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, het ophalen van de instellingen is niet gelukt.", "Fout!", ex.toString(), false);
        }
        return settings;
    }

    /**
     *
     * @param settings
     */
    public void saveSettings(Settings settings) {
        String sql = "UPDATE settings SET "
                + "x1 = '" + settings.getX1() + "', "
                + "x2 = '" + settings.getX2() + "', "
                + "x3 = '" + settings.getX3() + "';";
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            dbManager.executeUpdate(sql);
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("Fout opgetreden, het opslaan van de instellingen is niet gelukt.", "Fout!", ex.toString(), false);
        }
    }

    /**
     *
     * @param message
     */
    public void addToLog(String message) {
        String sql = "INSERT INTO log (message) VALUES ('" + message + "');";
        if (DEBUG) {
            System.err.println(sql);
        }
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            if (DEBUG) {
                ex.printStackTrace();
            }
            Utils.showMessage("ERNSTIGE FOUT, er is een fout opgetreden bij het loggen van een fout.", "Fout!", ex.toString(), true);
        }
    }
}