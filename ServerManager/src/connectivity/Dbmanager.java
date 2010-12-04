package connectivity;

import java.sql.*;

public class Dbmanager {
    public static final String JDBC_EXCEPTION = "JDBC Exception: ";
    public static final String SQL_EXCEPTION = "SQL Exception: ";

    public String CurrentDatabase = "";

    public Connection connection;

    /**
     * Open database connection
     */
    public void openConnection(String Databasename)
    {
        if (!CurrentDatabase.equals(Databasename))
        {
            closeConnection();
            try {
                Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://localhost/" + Databasename;
                String user = "root", pass = "mangos";
                CurrentDatabase = Databasename;

                /** Open connection */
                connection = DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException e) {
                System.err.println(JDBC_EXCEPTION + e);
            } catch (java.sql.SQLException e) {
                System.err.println(SQL_EXCEPTION + e);
            }
        }
        else
            return;
    }

    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
            else
                return;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Executes a query without result.
     * @param query, the SQl query
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
     * @param query, the SQL query
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
     * @param query, the SQL query
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
