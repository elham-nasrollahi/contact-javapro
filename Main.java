package fileordatabase;

import fileordatabase.database.ContactDatabase;
import java.util.Scanner;

/**
 * Created by Elham on 4/5/2020.
 */
public class Main {
    static Scanner in = new Scanner(System.in);

    private static void choose(Persistent fileOrDatabase){

        System.out.println("Please choose one of the insert,delete,show: ");
        String choise,count = "y";

        while (count.equalsIgnoreCase("y")) {
            choise = in.nextLine();
            switch (choise) {
                case "show":
                    fileOrDatabase.getAllPerson();
                    break;
                case "delete":
                    fileOrDatabase.delete();
                    break;
                case "insert":
                    fileOrDatabase.insert();
                    break;
                default:
                    System.out.println("Wrong input!");
            }
            System.out.println("Do you want to continue? Y/N");
            count = in.nextLine();
        }
    }



    public static void main(String[] args) throws Exception {

        System.out.println("This is your contact! ");
        Persistent database2 = new ContactDatabase();
        choose(database2);

    }
}
