import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//import java.util.*;


public class JavaSQLProgram {
    private static Connection c = null;
    private static Statement stml = null;

    private static final String people_db_name = "people.db";
    private static final String people_db_statement = "users";

    private static final String documents_db_name = "documents.db";

    public static void main(String ... args) {
        JavaSQLProgram pr = new JavaSQLProgram();

            // Opened database (" + people_db_name + ") successfully!"
        pr.openPeopleDb();
        pr.openDocumentsDb();

        pr.attachUsers();
        pr.peopleCreateTable();
        /* ************************* */
        pr.insert();

        ///////////////////////
        pr.close();
    }

    void openPeopleDb(){
        // Connection to people_db_name
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + people_db_name);
            System.out.println("DataBase opening (" + people_db_name + ") was successful!");

        } catch (Exception ex) {
            System.out.println("ERROR [" + people_db_name + "]");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }

    void peopleCreateTable(){
        try {
            stml = c.createStatement();
            String sql = "CREATE TABLE " + people_db_name + "." + people_db_statement + " (" +
                    "id                         INTEGER         PRIMARY KEY AUTOINCREMENT," +
                    "firstname                  TEXT            NOT NULL," +
                    "lastname                   TEXT            NOT NULL," +
                    "patronymic                 TEXT            NOT NULL," +
                    "birthday                   NUMERIC         NOT NULL," +  /* должно быть NUMERIC */
                    "gender                     Char(1)         NOT NULL);";
            // ARMY - использовать null, если GENDER == 'Ж' и не выводить на экран
            stml.executeUpdate(sql);
            stml.close();

        } catch (Exception ex) {
            System.out.println("ERROR [" + people_db_name + "]: CreateTableError");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }

    void attachUsers(){
        try {
            String sql = "ATTACH DATABASE '" + people_db_name + "' As '" + people_db_statement + "';";
            stml.executeUpdate(sql);
            stml.close();

        } catch (Exception ex) {
            System.out.println("ERROR [" + people_db_name + "]: CreateTableError");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }

    void openDocumentsDb(){
        // Connection to documents_db_name
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + documents_db_name);
            System.out.println("DataBase opening (" + documents_db_name + ") was successful!");

        } catch (Exception ex) {
            System.out.println("ERROR [" + documents_db_name + "]");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }

    void insert(){
        // TODO: обработка неправильного ввода

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter FirstName: ");  // проверка (слово)
        String fn = sc.nextLine();

        System.out.print("Enter LastName: ");  // проверка (слово)
        String ln = sc.nextLine();

        System.out.print("Enter patronymic: ");  // проверка (слово)
        String p = sc.nextLine();

        System.out.print("Enter your birthday: ");  // проверка (дата)
        String b = sc.nextLine();

        System.out.print("Enter your gender: ");  // проверка на 'M' и 'Ж', 
        char s = (sc.nextLine()).charAt(0);

        // после "Enter your gender: " реализовать возможность ввода ещё нескольких документов

        /* ********************* */

        

        /*/////////////*/// после удачного заполнения данных

        //Person P = new Person(fn, ls, p, b, s, g, army);

        String mess =
                "INSERT INTO " + people_db_statement + " (FIRSTNAME, LASTNAME, PATRONYMIC, BIRTHDAY, GENDER) " +
                        "VALUES ('" + fn + "', '" + ln + "', '" + p + "', '" + b + "', '" + s +"');";

        try {
            Statement st = c.createStatement();
            st.executeUpdate(mess);
            st.close();
        } catch (Exception ex) {

            System.out.println("ERROR [" + people_db_name + "] insert: ExecuteUpdateError");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }

    void close(){
        try {
            c.close();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }
}
/*
class Person {
    private static int countid = 0;
    //public static final int COUNT = 5;  (столбец с id не учитывался)

    //private int id;
    private String first_name;
    private String last_name;
    private String patronymic;
    private int birthday;  // class: Date, Calendar
    private char sex;
    private String g_army;

    Person(String first_name, String last_name, String patronymic, int birthday, char sex, String army){
        //id = ++countid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.sex = sex;
        this.g_army = army;
    }
}

 */