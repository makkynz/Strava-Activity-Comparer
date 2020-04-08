package stravacustom.data;

import stravacustom.utils.ConfigHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection implements DbConnectionInterface, AutoCloseable  {
    private Connection connection;

    public DbConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(ConfigHelper.get("db.url"), ConfigHelper.get("db.username"), ConfigHelper.get("db.password"));
        } catch (SQLException e) {
           throw new RuntimeException(e);
    }

        System.out.println("Connected to the PostgreSQL server successfully.");


    }

    @Override
    public Connection getConnection(){
        return connection;
    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
