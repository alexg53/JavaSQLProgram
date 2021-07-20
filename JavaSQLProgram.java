import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//import java.util.*;


public class JavaSQLProgram {
    public static void main(String ... args) {
        open();
        insert();
        close();
    }

    private static Connection c;
    static void open(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:people.db");
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }

    static void insert(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter FirstName: ");
        String fn = sc.nextLine();

        System.out.print("Enter LastName: ");
        String ln = sc.nextLine();

        System.out.print("Enter patronymic: ");
        String p = sc.nextLine();

        System.out.print("Enter your birthday: ");
        String b = sc.nextLine();

        //System.out.print("Enter your gender: ");
        //char s = sc.nextChar();

        ///// после удачного заполнения данных

        //Person P = new Person(fn, ls, p, b, s);
        //s - 

        /*
        
        String mess = 
            "INSERT INTO users (..) " +
            "VALUES ('', '', ..);";

        */


    }

    static void close(){
        try {
            c.close();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }
}

class Person {
    private static int countid = 0;
    //public static final int COUNT = 5;  столбец с id не учитывался

    private int id;
    private String first_name;
    private String last_name;
    private String patronymic;
    private int birthday;  // class: Date, Calendar
    private char sex;

    Person(String first_name, String last_name, String patronymic, int birthday, char sex){
        id = ++countid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.sex = sex;

        if (sex == 'M'){
            //добавить документы
        }

        class addDocuments{
            private int id;
            private String name;

            addDocuments(String newname){
                this.id = ++countid;
                this.name = newname;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s | first_name: %s | last_name: %s | patronymic: %s | birthday: %s | boolean: %s ", this.id, this.first_name, this.last_name, this.patronymic, this.birthday, this.sex);
    }
}