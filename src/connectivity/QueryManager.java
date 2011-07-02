package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.WorkHours;

/**
 *
 * @author UDP
 */
public class QueryManager {

    private final Dbmanager dbmanager;

    /**
     * Sla het Dbmanager object op voor intern (private) gebruik
     * @param dbmanager
     */
    public QueryManager(Dbmanager dbmanager) {
        this.dbmanager = dbmanager;
    }

    public Employee getEmployee(int id) {
        Employee employee = new Employee();
        try {
            String sql = "SELECT * FROM medewerkers WHERE personeelsnummer = '" + id + "';";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                employee = new Employee(
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
                        result.getBoolean("admin"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    public Employee getEmployee(String firstName, String insertion, String familyName) {
        Employee employee = new Employee();
        try {
            String sql = "SELECT * FROM medewerkers WHERE voornaam = '" + firstName + "' AND ";
            if (!insertion.isEmpty()) {
                sql += "tussenvoegsel = '" + insertion + "' AND ";
            }
            sql += "achternaam = '" + familyName + "';";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                employee = new Employee(
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
                        result.getBoolean("admin"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM medewerkers;";
            ResultSet result = dbmanager.doQuery(sql);
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
                        result.getBoolean("admin")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public void addEmployee(Employee employee) {
        int fulltime = employee.isFullTime() ? 1 : 0;
        int parttime = employee.isPartTime() ? 1 : 0;
        int oproepkracht = employee.isCallWorker() ? 1 : 0;
        int baliemedewerker = employee.isClerk() ? 1 : 0;
        int museumdocent = employee.isMuseumEducator() ? 1 : 0;
        int admin = employee.isAdmin() ? 1 : 0;
        String sql = "INSERT INTO medewerkers (personeelsnummer, voornaam, achternaam, wachtwoord, fulltime, parttime, oproepkracht, baliemedewerker, museumdocent, contracturen, vakantiepercentage, admin)"
                + "VALUES('" + employee.getEmployeeNumber() + "', '" + employee.getFirstName().replace("'", "\'") + "', '"
                + employee.getFamilyName().replace("'", "\'") + "', '" + employee.getPassword().replace("'", "\'") + "', '" + fulltime + "', '"
                + parttime + "', '" + oproepkracht + "', '" + baliemedewerker + "', '" + museumdocent + "', '" + employee.getContractHours() + "', '" + employee.getVacationPercentage() + "', '" + admin + "')";
        dbmanager.insertQuery(sql);
    }

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
        dbmanager.insertQuery(sql);
    }

    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM medewerkers WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "';";
        dbmanager.insertQuery(sql);
    }

    public List<Employee> searchEmployee(String voornaam, String achternaam) {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM medewerkers WHERE ";
            if (voornaam.length() > 0) {
                sql += "(SELECT voornaam REGEXP '^" + voornaam + ".*') = 1";
            }
            if (voornaam.length() > 0 && achternaam.length() > 0) {
                sql += " AND ";
            }
            if (achternaam.length() > 0) {
                sql += "(SELECT achternaam REGEXP '^" + achternaam + ".*') = 1";
            }
            ResultSet result = dbmanager.doQuery(sql);
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
                        result.getBoolean("admin")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public List<WorkHours> getWorkHours(Employee employee) {
        List<WorkHours> hours = new ArrayList<WorkHours>();
        try {
            String sql = "SELECT * FROM werktijden WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "';";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                hours.add(new WorkHours(
                        employee, result.getString("datum"), result.getString("ingeroosterd"),
                        result.getDouble("gewerkt"), result.getDouble("compensatie150"), result.getDouble("compensatie200"),
                        result.getDouble("vakantie"), result.getDouble("adv"), result.getDouble("ziekte"),
                        result.getDouble("verlof"), result.getDouble("project")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hours;
    }

    public boolean workHourExists(Employee employee, String date) {
        boolean exists = false;
        try {
            String sql = "SELECT * FROM werktijden WHERE personeelsnummer = '" + employee.getEmployeeNumber() + "' AND datum = '" + date + "';";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                exists = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    public void updateWorkHours(WorkHours hour) {
        String sql = "";
        if (workHourExists(hour.getEmployee(), hour.getDate())) {
            sql = "UPDATE werktijden SET "
                    + "ingeroosterd = " + hour.getShouldWork() + ", "
                    + "gewerkt = " + hour.getWorked() + ", "
                    + "compensatie150 = " + hour.getCompensation150() + ", "
                    + "compensatie200 = " + hour.getCompensation200() + ", "
                    + "vakantie = " + hour.getVacation() + ", "
                    + "adv = " + hour.getADV() + ", "
                    + "ziekte = " + hour.getIllness() + ", "
                    + "verlof = " + hour.getLeave() + ", "
                    + "project = " + hour.getProject() + " "
                    + "WHERE personeelsnummer = " + hour.getEmployee().getEmployeeNumber() + " "
                    + "AND datum = " + hour.getDate()
                    + ";";
        } else {
            sql = "INSERT INTO werktijden VALUES ('"
                    + hour.getEmployee().getEmployeeNumber()
                    + "', '" + hour.getDate()
                    + "', '" + hour.getShouldWork()
                    + "', '" + hour.getWorked()
                    + "', '" + hour.getCompensation150()
                    + "', '" + hour.getCompensation200()
                    + "', '" + hour.getVacation()
                    + "', '" + hour.getADV()
                    + "', '" + hour.getIllness()
                    + "', '" + hour.getLeave()
                    + "', '" + hour.getProject()
                    + "');";
        }
        dbmanager.insertQuery(sql);
    }
}