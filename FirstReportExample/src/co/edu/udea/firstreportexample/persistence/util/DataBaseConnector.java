package co.edu.udea.firstreportexample.persistence.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel Ángel Ossa
 */
public class DataBaseConnector {

    private Connection connection;

    static String DATABASE = "reportexample";
    static String USER = "root";
    static String PASSWORD = "root";
    static String URL = "localhost:";
    static String PORT = "3306/";
    static String SERVER = "jdbc:mysql://" + URL + PORT + DATABASE;

    public DataBaseConnector() {
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(SERVER, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Imposible realizar la conección con la base de"
                    + " datos.");
            Logger.getLogger(DataBaseConnector.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Los parámetros para realizar la conección con"
                    + " la base de datos no son correctos.");
            Logger.getLogger(DataBaseConnector.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("No se ha podido cerrar la conexión con la"
                        + " base de datos.");
                Logger.getLogger(DataBaseConnector.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    public void destroy() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("No se ha podido destruir la conexión con la"
                        + " base de datos.");
                Logger.getLogger(DataBaseConnector.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

}
