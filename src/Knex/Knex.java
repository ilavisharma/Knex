package Knex;

import java.sql.*;

public class Knex {
    private Statement statement;
    Connection connection;

    public Knex(String type, Client client) throws SQLException, ClassNotFoundException, KnexException {
        type= type.toLowerCase();

//        Create the connection string
        String driverClass;
        String connectionString;
        if (type.equals("mysql")) {
            connectionString = "jdbc:mysql://"+client.host+":"+client.port+"/"+client.database;
            driverClass = "com.mysql.jdbc.Driver";
        } else if (type.equals("oracle")) {
            connectionString = "jdbc:oracle:thin:@"+client.host+":"+client.port+":xe";
            driverClass = "oracle.jdbc.Driver.OracleDriver";
        } else {
            throw new KnexException("The database ' "+ type + " ' is not supported");
        }

//        Connect to the Database
        Class.forName(driverClass);
        connection= DriverManager.getConnection(connectionString, client.user, client.password);
        statement= connection.createStatement();
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
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

    public static void printResult(int rows) {
        if (rows < 2)
            System.out.println(rows+" row affected");
        else
            System.out.println(rows+" rows affected");
    }

    public static void printResult(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData= rs.getMetaData();
        int columnCount= metaData.getColumnCount();

        String row="";
        for (int i = 1; i < (columnCount+1); i++) {
//            System.out.print(metaData.getColumnName(i).toUpperCase()+", ");
            row= row.concat(metaData.getColumnName(i).toUpperCase()+", ");
        }
        System.out.println(Query.removeLastComma(row));

        while (rs.next()) {
            row="";
            for (int i = 1; i < (columnCount+1); i++) {
                row= row.concat(rs.getString(i)+", ");
            }
            System.out.println(Query.removeLastComma(row));
        }

    }

}
