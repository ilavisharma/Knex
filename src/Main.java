import Knex.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Client client= new Client("localhost","root", "", "java", 3306);

        try {

            Knex db= new Knex("mysql", client);

            Object[][] values= {
                    {"Iris", "West"}, {"Oliver", "Queen"}, {"Gary", "Green"}, {"Rei", "Kovacs"}
            };
            String[] columns= {"first_name", "last_name"};

            Query query1= new Query("users")
                    .insert(columns, values);

            Query query2= new Query("users")
                    .update(new String[]{"first_name"}, new Object[]{"Laurel"})
                    .where("last_name", "Lance");

            System.out.println(query2.getRawSql());

            int rowsAffected= db.executeUpdate(query1);
            int rowsAffected2= db.executeUpdate(query2);

            System.out.println(rowsAffected+"  "+rowsAffected2);

        } catch (ClassNotFoundException | SQLException | KnexException e) {
            e.printStackTrace();
        }

    }

}
