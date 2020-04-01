package stravacustom.data;

import java.sql.Connection;

public interface DbConnectionInterface {
    Connection getConnection();

    public void close();
}
