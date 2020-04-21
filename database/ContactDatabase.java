package fileordatabase.database;

import fileordatabase.Person;
import fileordatabase.Client;
import fileordatabase.database.dbconnection.DatabaseConnection;
import java.sql.*;
import java.util.Scanner;


/**
 * Created by Elham on 3/18/2020.
 */
 public class ContactDatabase implements Client {

    Person person = new Person();
    static Scanner in = new Scanner(System.in);
    ResultSet rs;
    DatabaseConnection databaseConnection = DatabaseConnection.getNewInstance();
    Connection connection = databaseConnection.getConnection();

    public ContactDatabase() throws SQLException {
    }

    public void show() {
        try {

            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM mycontact");
            while (rs.next()) {
                System.out.print(rs.getString("firstname") + " ");
                System.out.print(rs.getString("lastname") + " ");
                System.out.println(rs.getString("phonenumber") + " ");
            }
        }catch (SQLException e){}
    }

    public void delete() {
        try {

            System.out.println("Enter the FirstName: ");
            person.firstname = in.next();
            PreparedStatement st = connection.prepareStatement("DELETE FROM mycontact WHERE firstname = ?");
            st.setString(1, person.firstname);
            st.executeUpdate();
            System.out.println(person.firstname + " was deleted!");
            show();
        }catch (SQLException e){}
    }

    public void insert() {

            try {
                System.out.println("Enter the FirstName: ");
                person.firstname = in.next();
                System.out.println("Enter the LastName: ");
                person.lastname = in.next();
                System.out.println("Enter the PhoneNumber: ");
                person.phonenumber = in.next();

                PreparedStatement st = connection.prepareStatement("INSERT INTO mycontact VALUES (?,?,?)");
                st.setString(1, person.firstname);
                st.setString(2, person.lastname);
                st.setString(3, person.phonenumber);
                st.executeUpdate();
                System.out.println(person.firstname + " " + person.lastname + " was added!");
                show();
            }catch (SQLException e){}
    }
}
