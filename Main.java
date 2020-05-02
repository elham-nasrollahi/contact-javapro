package fileordatabase;

import fileordatabase.database.DaoImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Elham on 4/5/2020.
 */

public class Main {

    DaoImpl daoImpl = new DaoImpl();

    public static void main(String[] args) throws Exception {

        Main main2 = new Main();
        System.out.println("This is your contact! ");
        main2.choose();

    }

    private void upperCase() {

        List<Person> personList = daoImpl.getAllPerson();
        for (Person person : personList) {
            Person person1 =new Person();
            person1.setPersonid(person.getPersonid());
            person1.setFirstname(person.getFirstname().toUpperCase());
            person1.setLastname(person.getLastname().toUpperCase());
            person1.setPhonenumber(person.getPhonenumber());
            daoImpl.update(person1);
            System.out.println(person1.toString());
        }
    }

    private void lowerCase() {

        List<Person> personList = daoImpl.getAllPerson();
        for (Person person : personList) {
            Person person1 =new Person();
            person1.setPersonid(person.getPersonid());
            person1.setFirstname(person.getFirstname().toLowerCase());
            person1.setLastname(person.getLastname().toLowerCase());
            person1.setPhonenumber(person.getPhonenumber());
            daoImpl.update(person1);
            System.out.println(person1.toString());
        }

    }

    private void editRecord() {

        Person person = new Person();
        System.out.println("Insert the member whose you want to edit:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID: ");
        person.setPersonid(scanner.nextInt());
        System.out.println("Firstname: ");
        person.setFirstname(scanner.next());
        System.out.println("Lastname: ");
        person.setLastname(scanner.next());
        System.out.println("Phonenumber: ");
        person.setPhonenumber(scanner.next());
        daoImpl.update(person);
        System.out.println("The member was edited successfully! ");
    }



    private void show(List<Person> personList) {

        for (Person person : personList) {
            System.out.println(person.toString());
        }

    }


    private void choose() throws SQLException{

        Scanner scanner = new Scanner(System.in);
        String choise,count = "y";

        while (count.equalsIgnoreCase("y")) {
            System.out.println("Please choose one of the insert,delete,show,uppercase,lowercase,edit: ");
            choise = scanner.nextLine();
            switch (choise) {
                case "show":
                    List<Person> list = daoImpl.getAllPerson();
                    show(list);
                    break;
                case "delete":
                    daoImpl.delete();
                    break;
                case "insert":
                    daoImpl.insert();
                    break;
                case "u":
                    upperCase();
                    break;
                case "l":
                    lowerCase();
                    break;
                case "e":
                    editRecord();
                    break;
                default:
                    System.out.println("Wrong input!");
            }
            System.out.println("Do you want to continue? Y/N");
            count = scanner.nextLine();

        }
    }
}
