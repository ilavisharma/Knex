import Knex.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Client client= new Client("localhost","root", "", "java", 3306);

        try {

            Knex db= new Knex("mysql", client);

//            values to change
            HashMap<String, Object> hashMap= new HashMap<>();
            hashMap.put("last_name", "Smoak");

            Query query= new Query("users").update(hashMap).where("first_name", "Felicity");

            int rowsAffected= db.executeUpdate(query);

            System.out.println(rowsAffected+" rows affected");

        } catch (ClassNotFoundException | SQLException | KnexException e) {
            e.printStackTrace();
        }

    }

}
