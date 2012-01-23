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
            boolean tmpFulltime, boolean tmpParttime, boolean tmpOproepkracht, boolean tmpBalieMedewerker,
            boolean tmpMuseumDocent, double contractUren, double vakantiePercentage, boolean tmpAdmin) {
        int fulltime = tmpFulltime ? 1 : 0;
        int parttime = tmpParttime ? 1 : 0;
        int oproepkracht = tmpOproepkracht ? 1 : 0;
        int baliemedewerker = tmpBalieMedewerker ? 1 : 0;
        int museumdocent = tmpMuseumDocent ? 1 : 0;
        int admin = tmpAdmin ? 1 : 0;
        String sql = "INSERT INTO medewerkers (personeelsnummer, voornaam, tussenvoegsel, achternaam, wachtwoord, fulltime, parttime, oproepkracht, baliemedewerker, museumdocent, contracturen, vakantiepercentage, admin)"
                + "VALUES('" + personeelsNummer + "', '" + voornaam.replace("'", "\'") + "', '"
                + tussenvoegsel.replace("'", "\'") + "', '" + achternaam.replace("'", "\'") + "', '" + wachtwoord + "', '" + fulltime + "', '"
                + parttime + "', '" + oproepkracht + "', '" + baliemedewerker + "', '" + museumdocent + "', '" + contractUren + "', '" + vakantiePercentage + "', '" + admin + "')";
        dbManager.insertQuery(sql);
    }

    // ToDo : Uitbreiden met de dagen waarop iemand niet wilt werken
    public void changeEmployee(Employee employee) {
        int fulltime = employee.isFullTime() ? 1 : 0;
        int parttime = employee.isPartTime() ? 1 : 0;
        int oproepkracht = employee.isCallWorker() ? 1 : 0;
        int baliemedewerker = employee.isClerk() ? 1 : 0;
        int museumdocent = employee.isMuseumEducator() ? 1 : 0;
        int admin = employee.isAdmin() ? 1 : 0;
        String sql = "UPDATE medewerkers SET "
                + "voornaam = '" + employee.getFirstName().replace("'", "\'") + "', "
                + "tussenvoegsel = '" + employee.getInsertion().replace("'", "\'") + "', "
                + "achternaam = '" + employee.getFamilyName().replace("'", "\'") + "', "
                + "wachtwoord = '" + employee.getPassword().replace("'", "\'") + "', "
                + "fulltime = '" + fulltime + "', "
                + "parttime = '" + parttime + "', "
                + "oproepkracht = '" + oproepkracht + "', "
                + "baliemedewerker = '" + baliemedewerker + "', "
                + "museumdocent = '" + museumdocent + "', "
                + "contracturen = '" + employee.getContractHours() + "', "
                + "vakantiepercentage = '" + employee.getVacationPercentage() + "', "
                + "admin = '" + admin + "' "
                + "WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "';";
        dbManager.insertQuery(sql);
    }

    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM medewerkers WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "';";
        dbManager.insertQuery(sql);
    }

    // ToDo : Porten naar RoosterProgramma.java om gebruik te maken van de al-aanwezige werknemers
    public ArrayList<Employee> searchEmployee(String voornaam, String achternaam) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        String sql = "SELECT * FROM medewerkers WHERE ";
        if (!voornaam.isEmpty()) {
            sql += "(SELECT voornaam REGEXP '" + voornaam + "') = 1";
        }
        if (!voornaam.isEmpty() && !achternaam.isEmpty()) {
            sql += " AND ";
        }
        if (!achternaam.isEmpty()) {
            sql += "(SELECT achternaam REGEXP '" + achternaam + "') = 1";
        }
        if (!voornaam.isEmpty() || !achternaam.isEmpty()) {
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
                            result.getBoolean("workmfriday"),
                            result.getBoolean("worksaturday"),
                            result.getBoolean("worksunday")));
                }
            } catch (SQLException ex) {
                Utils.showMessage("Fout opgetreden, kon niet zoeken naar deze gebruiker.", "Fout!", true, ex.getMessage());
            }
        } else {
            return getEmployees();
        }
        return employees;
    }

    // ToDo: WorkHours constructor maken met alleen employeenumber en date
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
                hours.setEmployeeNumber(employeeNumber);
                hours.setDate(date);
            }
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, kon niet de werkuren van personeelsnummer '" + employeeNumber + "' ophalen.", "Fout!", true, ex.getMessage());
        }
        return hours;
    }

    // ToDo: Checks weghalen uit de ELSE, je kan niet opslaan zonder wijziging
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
            if (!hour.getShouldWork().isEmpty()
                    || hour.getWorked() > 0
                    || hour.getVacation() > 0
                    || hour.getADV() > 0
                    || hour.getIllness() > 0
                    || hour.getLeave() > 0
                    || hour.getWithdrawnCompensation() > 0) {
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
            } else {
                return false;
            }
        }
        dbManager.insertQuery(sql);
        return true;
    }

    public Settings getSettings() {
        Settings settings = new Settings();
        String sql = "SELECT * FROM settings;";
        ResultSet result = dbManager.doQuery(sql);
        try {
            if (result.next()) {
                settings.setSettings(
                        result.getString("x1"),
                        result.getString("x2"),
                        result.getString("x3"));
            }
        } catch (SQLException ex) {
            Utils.showMessage("Fout opgetreden, het opslaan van de instellingen is niet gelukt.", "Fout!", true, ex.getMessage());
        }
        return settings;
    }

    public void saveSettings(Settings settings) {
        String sql = "UPDATE settings SET "
                + "x1 = '" + settings.getX1() + "', "
                + "x2 = '" + settings.getX2() + "', "
                + "x3 = '" + settings.getX3() + "';";
        dbManager.insertQuery(sql);
    }

    // ToDo: Datum toevoegen aan elke log entry
    public void addToLog(String message) {
        String sql = "INSERT INTO `log` (`message`) VALUES('" + message + "');";
        dbManager.insertQuery(sql);
    }
}