package nl.can.project.pspw.app.datasources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

    private Connection connection = null;
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public DbConnection() {
        connect();
    }

    private void connect() {
        try {
            Properties properties = new Properties();
            properties.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("database.properties")));
            Class.forName(properties.getProperty("driver"));

            connection = DriverManager.getConnection(
                    properties.getProperty("connectionString"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));

        } catch (SQLException | ClassNotFoundException | IOException e) {
            LOGGER.log(Level.SEVERE, "An exception was thrown.", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
