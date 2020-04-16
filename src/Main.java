import Knex.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Client client= new Client("localhost","root", "", "java", 3306);

        try {

            Knex db= new Knex("mysql", client);
            Query query= new Query("users").select("first_name", "last_name").where("first_name", "Felicity");



            ResultSet resultSet= db.executeQuery(query);
            while (resultSet.next()) {
//                System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));

            }

        } catch (ClassNotFoundException | SQLException | KnexException e) {
            e.printStackTrace();
        }

    }

}
