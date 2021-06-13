package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static DBConnector instance;
    private Connection connection;


    public static DBConnector getInstance(){
        if(instance == null){
            instance = new DBConnector();
        }
        return instance;
    }

    private DBConnector(){
        if(connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Unable to use MySQL. " + e.getMessage());
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java_s12","root","mercedes");
            } catch (SQLException e) {
                System.out.println("Error communicating with MySQL Database. " + e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
