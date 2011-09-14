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
    private static final String sqlexpress_instance = "ZAANSMUSEUM", sqlexpress_address = "zm-server11", sqlexpress_user = "sa", sqlexpress_pass = "";
    private static final String mysql_port = "3306", mysql_address = "127.0.0.1", mysql_user = "root", mysql_pass = "mangos";
    private static final String databasename = "Roosterprogramma";
    public Connection connection;
    private static boolean mysql = true;   // Meteen mysql proberen of eerst MS SQL

    /**
     * Open database connection
     */
    public void openConnection() {
        if (mysql || !openConnection(false)) {
            if (!openConnection(true)) {
                RoosterProgramma.getInstance().shutdown();
            }
        }
    }

    private boolean openConnection(boolean mysql) {
        boolean connected = false;
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
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                url = "jdbc:sqlserver://" + sqlexpress_address
                        + "\\" + sqlexpress_instance
                        + ";databaseName=" + databasename
                        + ";userName=" + sqlexpress_user
                        + ";password=" + sqlexpress_pass;
            }
            connection = DriverManager.getConnection(url);
            connected = true;
        } catch (ClassNotFoundException e) {
            System.err.println(JDBC_EXCEPTION + e);
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
        }
        return connected;
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
            System.err.println("Query:" + query);
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
            System.err.println("Query:" + query);
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
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            result = statement.getGeneratedKeys();
        } catch (java.sql.SQLException e) {
            System.err.println(SQL_EXCEPTION + e);
            System.err.println("Query:" + query);
        }
        return result;
    }
}