package stravacustom.data;

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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bank_analyser", "postgres", "T1g3rf00t1981");
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
