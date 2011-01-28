package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.WorkHours;
import roosterprogramma.RoosterProgramma;

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
                    result.getString("tussenvoegsel"),
                    result.getString("achternaam"),
                    result.getString("wachtwoord"),
                    result.getBoolean("fulltime"),
                    result.getBoolean("parttime"),
                    result.getBoolean("oproepkracht"),
                    result.getBoolean("baliemedewerker"),
                    result.getBoolean("museumdocent"),
                    result.getDouble("contracturen"),
                    result.getBoolean("admin")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    public Employee getEmployee(String firstName, String insertion, String familyName) {
        Employee employee = new Employee();
        try {
            String sql = "SELECT * FROM `medewerkers` WHERE `voornaam` = '" + firstName + "' AND ";
            if (!insertion.isEmpty())
                sql += "`tussenvoegsel` = '" + insertion + "' AND ";
            sql += "`achternaam` = '" + familyName + "';";
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
                        result.getString("tussenvoegsel"),
                        result.getString("achternaam"),
                        result.getString("wachtwoord"),
                        result.getBoolean("fulltime"),
                        result.getBoolean("parttime"),
                        result.getBoolean("oproepkracht"),
                        result.getBoolean("baliemedewerker"),
                        result.getBoolean("museumdocent"),
                        result.getDouble("contracturen"),
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
        int baliemedewerker = employee.isClerk() ? 1 : 0;
        String sql = "INSERT INTO `medewerkers` (personeelsnummer, voornaam, achternaam, wachtwoord, fulltime, parttime, contracturen, oproepkracht, baliemedewerker)"
                + "VALUES('" + employee.getEmployeeNumber() + "', '" + employee.getFirstName().replace("'", "\'") + "', '"
                + employee.getFamilyName().replace("'", "\'") + "', '" + employee.getPassword().replace("'", "\'") + "', '" + fulltime + "', '"
                + parttime + "', '" + employee.getContractHours() + "', '" + oproepkracht + "', '" + baliemedewerker + "')";
        dbmanager.insertQuery(sql);

        sql = "ALTER TABLE `werktijden` ADD COLUMN `" + employee.getFullName() + "` VARCHAR(255) DEFAULT '0;0;0;0;0;0;0;0';"; // Moet dynamisch qua velden met ;
        dbmanager.insertQuery(sql);
    }

    public void changeEmployee(Employee employee) {
        Employee oldEmployee = getEmployee(employee.getEmployeeNumber());
        int fulltime = employee.isFullTime() ? 1 : 0;
        int parttime = employee.isPartTime() ? 1 : 0;
        int oproepkracht = employee.isCallWorker() ? 1 : 0;
        int baliemedewerker = employee.isClerk() ? 1 : 0;
        String sql = "REPLACE INTO `medewerkers` (personeelsnummer, voornaam, achternaam, wachtwoord, fulltime, parttime, contracturen, oproepkracht, baliemedewerker)"
                + "VALUES('" + employee.getEmployeeNumber() + "', '" + employee.getFirstName().replace("'", "\'") + "', '"
                + employee.getFamilyName().replace("'", "\'") + "', '" + employee.getPassword().replace("'", "\'") + "', '" + fulltime + "', '"
                + parttime + "', '" + employee.getContractHours() + "', '" + oproepkracht + "', '" + baliemedewerker + "');";
        dbmanager.insertQuery(sql);

        sql = "ALTER TABLE `werktijden` CHANGE `" + oldEmployee.getFullName() + "` `" + employee.getFullName() + "` varchar(255) character set latin1 collate latin1_swedish_ci default '0;0;0;0;0;0;0;0;0' NOT NULL;"; // Moet dynamisch qua velden met ;
        dbmanager.insertQuery(sql);
    }
    
    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM `medewerkers` WHERE `personeelsnummer` = '" + employee.getEmployeeNumber() + "';";
        dbmanager.insertQuery(sql);

        sql = "ALTER TABLE `werktijden` DROP COLUMN `" + employee.getFullName() + "`;";
        dbmanager.insertQuery(sql);
    }

    public List<Employee> searchEmployee(String voornaam, String achternaam) {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            String sql = "SELECT * FROM `medewerkers` WHERE ";
            if(voornaam.length() > 0)
            {
                sql += "(SELECT `voornaam` REGEXP '^"+ voornaam + ".*') = 1";
            }
            if(voornaam.length() > 0 && achternaam.length() > 0)
            {
                sql += " AND ";
            }
            if(achternaam.length() > 0)
            {
                sql += "(SELECT `achternaam` REGEXP '^"+ achternaam + ".*') = 1";
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
                        result.getBoolean("admin")
                    )
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public List<WorkHours> getWorkHours(Employee employee) {
        List<WorkHours> hours = new ArrayList<WorkHours>();
        try {
            String sql = "SELECT `" + employee.getFullName() + "`, `datum` FROM `werktijden`;";
            ResultSet result = dbmanager.doQuery(sql);
            while (result.next()) {
                hours.add(new WorkHours(
                    employee, result.getString("datum"), result.getString(employee.getFullName())
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hours;
    }

    public boolean workHourExists(Employee employee, String date) {
        boolean exists = false;
        try {
            String sql = "SELECT `" + employee.getFullName() + "` FROM `werktijden` WHERE `datum` = '" + date + "';";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                exists = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    public void updateWorkHours(WorkHours hours) {
        String data = hours.getShouldWork() + ";"
                + hours.getWorked() + ";"
                + hours.getCompensation150() + ";"
                + hours.getCompensation200() + ";"
                + hours.getVacation() + ";"
                + hours.getADV() + ";"
                + hours.getIllness() + ";"
                + hours.getLeave() + ";"
                + hours.getProject();
        String sql = "";
        if (workHourExists(hours.getEmployee(), hours.getDate()))
        {
            sql = "UPDATE `werktijden` SET `" + hours.getEmployee().getFullName() + "` = '" + data + "' WHERE `datum` = '" + hours.getDate() + "';";
        }
        else
        {
            sql = "INSERT INTO `werktijden` (datum, `" + hours.getEmployee().getFullName() + "`) VALUES ('" + hours.getDate() + "', '" + data + "');";
        }
        dbmanager.insertQuery(sql);
    }
}