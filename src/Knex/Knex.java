package Knex;

import java.sql.*;

public class Knex {
    private String connectionString;
    private String driverClass;
    private Client client;
    private Statement statement;

    public Knex(String type, Client client) throws SQLException, ClassNotFoundException, KnexException {
        this.client= client;
        type= type.toLowerCase();

//        Create the connection string
        if (type.equals("mysql")) {
            connectionString= "jdbc:mysql://"+client.host+":"+client.port+"/"+client.database;
            driverClass= "com.mysql.jdbc.Driver";
        } else if (type.equals("oracle")) {
            connectionString= "jdbc:oracle:thin:@"+client.host+":"+client.port+":xe";
            driverClass= "oracle.jdbc.Driver.OracleDriver";
        } else {
            throw new KnexException("The database ' "+ type + " ' is not supported");
        }
//        connect to the DB
        connect();
    }

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        Connection connection= DriverManager.getConnection(connectionString, client.user, client.password);
        statement= connection.createStatement();
    }

    public ResultSet rawExecuteQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public int rawUpdateQuery(String query) throws SQLException {
        return statement.executeUpdate(query);
    }

    public ResultSet executeQuery(Query query) throws SQLException {
            return statement.executeQuery(query.sql);
    }

    public int executeUpdate(Query query) throws SQLException {
        return statement.executeUpdate(query.sql);
    }

}
