package fileordatabase.database;

import fileordatabase.Person;
import fileordatabase.IDao;
import fileordatabase.database.dbconnection.DatabaseConnection;
import java.sql.*;
import java.util.*;


/**
 * Created by Elham on 3/18/2020.
 */
 public class DaoImpl implements IDao {


    static Scanner scanner = new Scanner(System.in);
    DatabaseConnection databaseConnection = DatabaseConnection.getNewInstance();
    Connection connection = databaseConnection.getConnection();


    public List<Person> getAllPerson() {

        List<Person> personList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mycontact");

            while (rs.next()){

                Person person = new Person();
                person.setFirstname(rs.getString("firstname"));
                person.setLastname(rs.getString("lastname"));
                person.setPhonenumber(rs.getString("phonenumber"));
                personList.add(person);

            }

        }catch (SQLException e){}

        return personList;
    }


    public void delete() {

        try {

            System.out.println("Enter the FirstName: ");
            String firstname = scanner.next();
            PreparedStatement st = connection.prepareStatement("DELETE FROM mycontact WHERE firstname = ?");
            st.setString(1, firstname);
            st.executeUpdate();
            System.out.println(firstname + " was deleted!");

        }catch (SQLException e){}
    }

    public void insert() {

            Person person = new Person();
            try {
                System.out.println("Enter the FirstName: ");
                person.setFirstname(scanner.next());
                System.out.println("Enter the LastName: ");
                person.setLastname(scanner.next());
                System.out.println("Enter the PhoneNumber: ");
                person.setPhonenumber(scanner.next());

                PreparedStatement st = connection.prepareStatement("INSERT INTO mycontact VALUES (?,?,?)");
                st.setString(1, person.getFirstname());
                st.setString(2, person.getLastname());
                st.setString(3, person.getPhonenumber());
                st.executeUpdate();
                System.out.println(person.getFirstname() + " " + person.getLastname() + " was added!");

            }catch (SQLException e){}
    }

    public void update(Person person) {

        //Person person = new Person();
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE mycontact SET firstname = ? , lastname = ? WHERE phonenumber = ?" );
            st.setString(1, person.getFirstname());
            st.setString(2, person.getLastname());
            st.setString(3, person.getPhonenumber());
            st.executeUpdate();
        }catch (SQLException e){}


    }


}
