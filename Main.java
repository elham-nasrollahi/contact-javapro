package fileordatabase;

import fileordatabase.database.ContactDatabase;
import fileordatabase.file.ContactFile;


import java.util.Scanner;

/**
 * Created by Elham on 4/5/2020.
 */
public class Main {

    public static void choose(Client fileOrDatabase){
        Scanner in = new Scanner(System.in);
        System.out.println("Please choose one of the insert,delete,show: ");
        String choise,count = "y";

        while (count.equalsIgnoreCase("y")) {
            choise = in.nextLine();
            switch (choise) {
                case "show":
                    fileOrDatabase.show();
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



    public static void main(String[] args) {

            try {
                Scanner in = new Scanner(System.in);
                System.out.println("This is your contact! ");
                System.out.println("Save to File or Database F/D: ");
                String choose = in.nextLine();

                switch (choose) {
                    case "F":
                        Client file = new ContactFile();
                        choose(file);
                        break;

                    case "D":
                        Client database2 = new ContactDatabase();
                        choose(database2);
                        break;
                }

            }catch (Exception e){}
        }
    }
