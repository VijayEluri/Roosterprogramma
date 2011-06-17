package connectivity;

import java.sql.*;
import roosterprogramma.RoosterProgramma;

/**
 *
 * @author UDP
 */
public class Dbmanager {

    public static final String JDBC_EXCEPTION = "JDBC Exception: ";
    public static final String SQL_EXCEPTION = "SQL Exception: ";
    private static final String sqlexpress_port = "1433", sqlexpress_instance = "ZAANSMUSEUM", sqlexpress_address = "zm-server11.zaansmuseum.local", sqlexpress_user = "", sqlexpress_pass = "";
    private static final String mysql_port = "3306", mysql_address = "127.0.0.1", mysql_user = "root", mysql_pass = "mangos";
    private static final String databasename = "roosterprogramma";
    public Connection connection;
    private static final boolean mysql = true;

    /**
     * Open database connection
     */
    public void openConnection() {
        try {
            String url = "";
            if (mysql) {
                Class.forName("com.mysql.jdbc.Driver");
                url = "jdbc:mysql://" + mysql_address
                        + ":" + mysql_port
                        + "/" + databasename
                        + "?user=" + mysql_user
                        + "&password=" + mysql_pass;
            } else {
                Class.forName("com.sqlserver.jdbc.SQLServerDriver");
                url = "jdbc:sqlserver://" + sqlexpress_address
                        + ":" + sqlexpress_port
                        + ";instance=" + sqlexpress_instance
                        + ";databaseName=" + databasename
                        + ";userName=" + sqlexpress_user
                        + ";password=" + sqlexpress_pass;
            }

            /** Open connection */
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.err.println(JDBC_EXCEPTION + e);
            RoosterProgramma.getInstance().shutdown();
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
            RoosterProgramma.getInstance().shutdown();
        }
    }

    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Executes a query without result.
     * @param query
     */
    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
    }

    /**
     * Executes a query with result.
     * @param query
     * @return 
     */
    public ResultSet doQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return result;
    }

    /**
     * Executes a query with result.
     * @param query 
     * @return
     */
    public ResultSet insertQuery(String query) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            result = statement.getGeneratedKeys();
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return result;
    }
}