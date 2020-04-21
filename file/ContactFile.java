package fileordatabase.file;

import fileordatabase.Client;
import fileordatabase.Person;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Created by Elham on 3/18/2020.
 */
 public class ContactFile implements Client{

    Person person = new Person();
    static Scanner in = new Scanner(System.in);



    public void show(){

        try {

            String row;
            FileReader fileReader = new FileReader("D:\\project\\javacontact2\\src\\savetofile\\context.txt");
            BufferedReader br = new BufferedReader(fileReader);

            while ((row = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(row, ",");
                System.out.println(st.nextToken() + " " + st.nextToken() + " " + st.nextToken());
            }
            br.close();
        }catch (IOException e){}
    }

    public void delete() {
            try {
                String row;
                File temp = new File("D:\\project\\javacontact2\\src\\savetofile\\temp.txt");
                File context = new File("D:\\project\\javacontact2\\src\\savetofile\\context.txt");
                BufferedReader br = new BufferedReader(new FileReader(context));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

                System.out.println("Enter the FirstName: ");
                person.firstname = in.next();

                while ((row = br.readLine()) != null) {
                    if (row.contains(person.firstname))
                        continue;
                    bw.write(row);
                    bw.flush();
                    bw.newLine();
                }

                br.close();
                bw.close();
                context.delete();
                temp.renameTo(context);
                System.out.println(person.firstname + " was deleted!");
                show();
            }catch (IOException e){}
    }

    public void insert() {

            try {

                FileWriter fr = new FileWriter("D:\\project\\javacontact2\\src\\savetofile\\context.txt", true);
                BufferedWriter bw = new BufferedWriter(fr);

                System.out.println("Enter the FirstName: ");
                person.firstname = in.next();
                System.out.println("Enter the LastName: ");
                person.lastname = in.next();
                System.out.println("Enter the PhoneNumber: ");
                person.phonenumber = in.next();

                bw.write(person.firstname + "," + person.lastname + "," + person.phonenumber);
                bw.flush();
                bw.newLine();
                bw.close();

                System.out.println(person.firstname + " " + person.lastname + " was added!");
                show();
            }catch (IOException e){}
    }
}
