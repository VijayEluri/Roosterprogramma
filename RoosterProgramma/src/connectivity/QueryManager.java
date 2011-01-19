package connectivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.EmployeeInfo;

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
            String sql = "SELECT * FROM `medewerkers` WHERE `id` = '" + id + "';";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                employee = new Employee(
                    result.getInt("id"),
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
                        result.getInt("id"),
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

    public EmployeeInfo getInfo(int id, int weeknr, int dag) {
        EmployeeInfo info = new EmployeeInfo();
        try {
            String sql = "SELECT * FROM `medewerkerinfo` WHERE `id` = '" + id + "' AND `weeknr` = '" + weeknr + "' AND `dag` = '" + dag + "';";
            ResultSet result = dbmanager.doQuery(sql);
            if (result.next()) {
                info = new EmployeeInfo(
                    result.getInt("contracturen"),
                    result.getInt("tewerken"),
                    result.getInt("werkelijkgewerkt"),
                    result.getInt("gewerkttebetalen"),
                    result.getInt("gewerkttecompenseren"),
                    result.getInt("mindergewerkt"),
                    result.getInt("atv"),
                    result.getInt("vakantie"),
                    result.getInt("compensatie33"),
                    result.getInt("compensatie50"),
                    result.getInt("compensatie100"),
                    result.getInt("dokter"),
                    result.getInt("ziekte"),
                    result.getInt("eigenrekening"),
                    result.getInt("feestdagem"),
                    result.getInt("compensatieopname"),
                    result.getInt("caoverlof")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }
}