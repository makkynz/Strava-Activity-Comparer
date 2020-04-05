package stravacustom.data;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedParamStatement implements Closeable {
    public NamedParamStatement(Connection conn, String sql)  {
        Pattern findParametersPattern = Pattern.compile("(?<!')(:[\\w]*)(?!')");
        Matcher matcher = findParametersPattern.matcher(sql);
        while (matcher.find()) {
            fields.add(matcher.group().substring(1));
        }
        try {
            prepStmt = conn.prepareStatement(sql.replaceAll(findParametersPattern.pattern(), "?"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement getPreparedStatement() {
        return prepStmt;
    }
    public ResultSet executeQuery()  {
        try {
            return prepStmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void executeNonQuery()  {
        try {
             prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void close() {
        try {
            prepStmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUUID(String name, UUID value)  {
        try {
            prepStmt.setObject(getIndex(name), value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setString(String name, String value)  {
        try {
            prepStmt.setString(getIndex(name), value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDate(String name, Date value) {
        try {
            prepStmt.setDate(getIndex(name),  new java.sql.Date(value.getTime()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInt(String name, int value)  {
        try {
            prepStmt.setInt(getIndex(name), value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getIndex(String name) {
        return fields.indexOf(name)+1;
    }
    private PreparedStatement prepStmt;
    private List<String> fields = new ArrayList<String>();
}