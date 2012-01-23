package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Employee;
import model.Settings;
import model.WorkHours;
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
                        result.getBoolean("workmonday"),
                        result.getBoolean("worktuesday"),
                        result.getBoolean("workwednesday"),
                        result.getBoolean("workthursday"),
                        result.getBoolean("workfriday"),
                        result.getBoolean("worksaturday"),
                        result.getBoolean("worksunday")));
            }
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, kan geen enkele gebruiker ophalen uit de database.", "Fout!", ex.getMessage(), false);
        }
        return employees;
    }

    // ToDo : Uitbreiden met de dagen waarop iemand niet wilt werken
    public void addEmployee(int personeelsNummer, String voornaam, String tussenvoegsel, String achternaam, String wachtwoord,
            boolean fulltime, boolean parttime, boolean oproepkracht, boolean baliemedewerker,
            boolean museumdocent, double contractUren, double vakantiePercentage, boolean admin) {
        String sql = "INSERT INTO medewerkers (personeelsnummer, voornaam, tussenvoegsel, achternaam, wachtwoord, fulltime, parttime, oproepkracht, baliemedewerker, museumdocent, contracturen, vakantiepercentage, admin)"
                + "VALUES('" + personeelsNummer + "', '" + voornaam.replace("'", "\'") + "', '"
                + tussenvoegsel.replace("'", "\'") + "', '" + achternaam.replace("'", "\'") + "', '" + wachtwoord + "', '" + Utils.booleanToInt(fulltime) + "', '"
                + Utils.booleanToInt(parttime) + "', '" + Utils.booleanToInt(oproepkracht) + "', '" + Utils.booleanToInt(baliemedewerker) + "', '" + Utils.booleanToInt(museumdocent) + "', '"
                + contractUren + "', '" + vakantiePercentage + "', '" + Utils.booleanToInt(admin) + "')";
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, nieuwe medewerker kon niet worden toegevoegd.", "Fout!", ex.getMessage(), false);
        }
    }

    // ToDo : Uitbreiden met de dagen waarop iemand niet wilt werken
    public void changeEmployee(Employee employee) {
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
                + "admin = '" + Utils.booleanToInt(employee.isAdmin()) + "' "
                + "WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "';";
        try {
            dbManager.insertQuery(sql);
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
            } else {
                hours = new WorkHours(employeeNumber, date);
            }
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, kon niet de werkuren van personeelsnummer '" + employeeNumber + "' ophalen.", "Fout!", ex.getMessage(), false);
        }
        return hours;
    }

    public boolean updateWorkHours(WorkHours hour) {
        String sql;
        if (!getWorkHours(hour.getEmployeeNumber(), hour.getDate()).getShouldWork().isEmpty()) {
            sql = "UPDATE werktijden SET "
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
        } else {
            sql = "INSERT INTO werktijden VALUES ('"
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
        }
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

    // ToDo: Datum toevoegen aan elke log entry
    public void addToLog(String message) {
        String sql = "INSERT INTO `log` (`message`) VALUES ('" + message + "');";
        try {
            dbManager.insertQuery(sql);
        } catch (SQLException ex) {
            Utils.showMessage("ERNSTIGE FOUT, er is een fout opgetreden bij het loggen van een fout.", "Fout!", ex.getMessage(), true);
        }
    }
}