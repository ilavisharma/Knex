package Knex;

public class Client {
    public String host, user, password, database;
    public int port;

    public Client(String host, String user, String password, String database, int port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
        this.port = port;
    }
}
