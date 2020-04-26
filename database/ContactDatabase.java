package fileordatabase.database;

import fileordatabase.Person;
import fileordatabase.Persistent;
import fileordatabase.database.dbconnection.DatabaseConnection;
import java.sql.*;
import java.util.*;


/**
 * Created by Elham on 3/18/2020.
 */
 public class ContactDatabase implements Persistent {

    Person person = new Person();
    static Scanner in = new Scanner(System.in);
    ResultSet rs;
    DatabaseConnection databaseConnection = DatabaseConnection.getNewInstance();
    Connection connection = databaseConnection.getConnection();
    List persons = new ArrayList();

    public ContactDatabase() throws SQLException {
    }

    private Person extractPerson (ResultSet rs) throws SQLException{
        person.setFirstname(rs.getString("firstname"));
        person.setLastname(rs.getString("lastname"));
        person.setPhonenumber(rs.getString("phonenumber"));

        return person;

    }


    public List getAllPerson() {

        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM mycontact");

            while (rs.next()){
                person = extractPerson(rs);
                persons.add(person);
                System.out.println(person.getFirstname() +" "+ person.getLastname()+ " "+ person.getPhonenumber());
            }
            //return persons;

        }catch (SQLException e){}

        return null;
    }


    public void delete() {

        try {

            System.out.println("Enter the FirstName: ");
            person.setFirstname(in.next());
            PreparedStatement st = connection.prepareStatement("DELETE FROM mycontact WHERE firstname = ?");
            st.setString(1, person.getFirstname());
            st.executeUpdate();
            System.out.println(person.getFirstname() + " was deleted!");

        }catch (SQLException e){}
    }

    public void insert() {

            try {
                System.out.println("Enter the FirstName: ");
                person.setFirstname(in.next());
                System.out.println("Enter the LastName: ");
                person.setLastname(in.next());
                System.out.println("Enter the PhoneNumber: ");
                person.setPhonenumber(in.next());

                PreparedStatement st = connection.prepareStatement("INSERT INTO mycontact VALUES (?,?,?)");
                st.setString(1, person.getFirstname());
                st.setString(2, person.getLastname());
                st.setString(3, person.getPhonenumber());
                st.executeUpdate();
                System.out.println(person.getFirstname() + " " + person.getLastname() + " was added!");

            }catch (SQLException e){}
    }
}
