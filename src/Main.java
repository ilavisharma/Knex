import Knex.Client;
import Knex.Knex;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Client client= new Client("localhost","root", "", "java", 3306);

        Knex db= new Knex("mysql", client);

        try {
            db.connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

}
