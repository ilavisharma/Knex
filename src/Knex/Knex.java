package Knex;

import java.sql.*;

public class Knex {
    private String connectionString;
    private String driverClass;
    private Client client;
    private Connection connection;

//    "jdbc:mysql://localhost:3306/database"
//    "jdbc:oracle:thin:@localhost:1521:xe"

    public Knex(String type, Client client) {
        this.client= client;

//        Create the connection string
        if (type.equals("mysql")) {
            connectionString= "jdbc:mysql://"+client.host+":"+client.port+"/"+client.database;
            driverClass= "com.mysql.jdbc.Driver";
        } else if (type.equals("oracle")) {
            connectionString= "jdbc:oracle:thin:@"+client.host+":"+client.port+":xe";
            driverClass= "oracle.jdbc.Driver.OracleDriver";
        }

    }

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);

        connection= DriverManager.getConnection(connectionString, client.user, client.password);

        execute("SELECT * FROM users");
    }

    private void execute(String query) throws SQLException {

        Statement statement= connection.createStatement();

        ResultSet resultSet= statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));

        }

    }
}
