package fileordatabase.database.dbconnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Elham on 3/18/2020.
 */
public class DatabaseConnection {
    private String databaseDriver;
    private String databaseConnectionUrl;
    private String databaseUsername;
    private String databasePassword;

    private Connection connection;

    private DatabaseConnection(){}
    private static DatabaseConnection databaseConnection = null;

    public Connection getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src\\fileordatabase\\database\\application.properties"));
            databaseDriver = properties.getProperty("fileordatabase.database.setting.databaseDriver");
            databaseConnectionUrl = properties.getProperty("fileordatabase.database.setting.databaseConnectionUrl");
            databaseUsername = properties.getProperty("fileordatabase.database.setting.databaseUsername");
            databasePassword = properties.getProperty("fileordatabase.database.setting.databasePassword");


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(databaseDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    databaseConnectionUrl,databaseUsername,databasePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static synchronized DatabaseConnection getNewInstance(){
        if (databaseConnection==null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }
}
