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
            String sql = "SELECT * FROM `medewerkers` WHERE `personeelsnummer` = '" + id + "';";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                employee = new Employee(
                    result.getInt("personeelsnummer"),
                    result.getString("voornaam"),
                    result.getString("achternaam"),
                    result.getString("wachtwoord"),
                    result.getBoolean("fulltime"),
                    result.getBoolean("parttime"),
                    result.getBoolean("oproepkracht"),
                    result.getBoolean("admin")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    public Employee getEmployee(String firstName, String familyName) {
        Employee employee = new Employee();
        try {
            String sql = "SELECT * FROM `medewerkers` WHERE `voornaam` = '" + firstName + "' AND `achternaam` = '" + familyName + "';";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                employee = new Employee(
                    result.getInt("personeelsnummer"),
                    result.getString("voornaam"),
                    result.getString("achternaam"),
                    result.getString("wachtwoord"),
                    result.getBoolean("fulltime"),
                    result.getBoolean("parttime"),
                    result.getBoolean("oproepkracht"),
                    result.getBoolean("admin")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM `medewerkers`;";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                employees.add(
                    new Employee(
                        result.getInt("personeelsnummer"),
                        result.getString("voornaam"),
                        result.getString("achternaam"),
                        result.getString("wachtwoord"),
                        result.getBoolean("fulltime"),
                        result.getBoolean("parttime"),
                        result.getBoolean("oproepkracht"),
                        result.getBoolean("admin")
                    )
                );
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
        String sql = "INSERT INTO `medewerkers` (personeelsnummer, voornaam, achternaam, wachtwoord, fulltime, parttime, oproepkracht)"
                + "VALUES('" + employee.getEmployeeNumber() + "', '" + employee.getFirstName().replace("'", "\'") + "', '"
                + employee.getFamilyName().replace("'", "\'") + "', '" + employee.getPassword().replace("'", "\'") + "', '" + fulltime + "', '"
                + parttime + "', '" + oproepkracht + "')";
        dbmanager.insertQuery(sql);
    }

    public void changeEmployee(Employee employee) {
        int fulltime = employee.isFullTime() ? 1 : 0;
        int parttime = employee.isPartTime() ? 1 : 0;
        int oproepkracht = employee.isCallWorker() ? 1 : 0;
        String sql = "REPLACE INTO `medewerkers` (personeelsnummer, voornaam, achternaam, wachtwoord, fulltime, parttime, oproepkracht)"
                + "VALUES('" + employee.getEmployeeNumber() + "', '" + employee.getFirstName().replace("'", "\'") + "', '"
                + employee.getFamilyName().replace("'", "\'") + "', '" + employee.getPassword().replace("'", "\'") + "', '" + fulltime + "', '"
                + parttime + "', '" + oproepkracht + "');";
        dbmanager.insertQuery(sql);
    }
    
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM `medewerkers` WHERE `personeelsnummer` = '" + id + "';";
        dbmanager.insertQuery(sql);
    }

    public List<WorkHours> getWorkHours(Employee employee, String year) {
        List<WorkHours> hours = new ArrayList<WorkHours>();
        try {
            String sql = "SELECT '" + employee.getFirstName() + " " + employee.getFamilyName() + "' FROM `werktijden` WHERE (SELECT `datum` REGEXP '" + year + "') = 1;";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                hours.add(new WorkHours(
                    result.getString("datum"), result.getString(employee.getFirstName() + " " + employee.getFamilyName())
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hours;
    }
}