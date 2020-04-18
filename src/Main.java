import Knex.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Client client= new Client("localhost","root", "", "java", 3306);

        try {

            Knex db= new Knex("mysql", client);

            Query q1= new Query("users")
                    .select("*");

            db.print(db.executeQuery(q1));

            db.close();

        } catch (ClassNotFoundException | SQLException | KnexException e) {
            e.printStackTrace();
        }

    }

}
