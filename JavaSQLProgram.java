import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
//import java.util.*;


public class JavaSQLProgram {
    private static final String path_to_dbs = "c:\\sqlite\\";

    private static Connection c = null;
    private static Connection c2 = null;

    private static Statement st = null;

    private static final String people_db_name = "people";  // + ".db";
    private static final String people_db_statement = "users";
    private static int id_people = 0;

    private static final String documents_db_name = "documents";
    private static final String documents_db_statement = "docs";
    private static int id_docs = 0;

    Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

    public static void main(String... args) {
        JavaSQLProgram pr = new JavaSQLProgram();

        // Opened database (" + people_db_name + ") successfully!"
        if (pr.openPeopleDb()) {
            pr.openDocumentsDb();


            //pr.peopleCreateTable();
            /* ************************* */
            pr.insertPeople();  // изменение (добавление) строчки в people.db <- users
            pr.select();

            ///////////////////////
            pr.close();
        } else {
            System.out.println("Невозможно открыть базу данных!");
        }

    }

    boolean openPeopleDb() {
        // Connection to people_db_name
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + path_to_dbs + people_db_name + ".db");
            System.out.println("DataBase opening (" + people_db_name + ".db) was successful!");
            return true;
        } catch (Exception ex) {

            System.out.println("ERROR [" + people_db_name + ".db]");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
            return false;
        }
    }

    /*
     void peopleCreateTable(){
         try {   " + people_db_name + ".
             st = c.createStatement();
             String sql = "CREATE TABLE " + people_db_name + ".db." + people_db_statement + " (" +
                     "id                         INTEGER         PRIMARY KEY AUTOINCREMENT, " +
                     "firstname                  TEXT            NOT NULL, " +
                     "lastname                   TEXT            NOT NULL, " +
                     "patronymic                 TEXT            NOT NULL, " +
                     "birthday                   NUMERIC         NOT NULL, " +   должно быть NUMERIC
                     "gender                     Char(1)         NOT NULL);";
              ARMY - использовать null, если GENDER == 'Ж' и не выводить на экран
             st.executeUpdate(sql);
             st.close();

         } catch (Exception ex) {
             System.out.println("ERROR [" + people_db_name + ".db]: CreateTableError");
             System.err.println(ex.getClass().getName() + ": " + ex.getMessage() );
             System.exit(0);
         }
     }
     void attachUsers() {
         try {
             String sql = "ATTACH DATABASE '" + people_db_name + ".db' As '" + people_db_statement + "';";
             st.executeUpdate(sql);

         } catch (Exception ex) {
             System.out.println("ERROR [" + people_db_name + ".db]: CreateTableError");
             System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
             System.exit(0);
         }
     }
    */


    void select() {
        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, firstname, lastname, patronymic, birthday, gender  FROM users ORDER BY id");

            System.out.println(" ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String fn = rs.getString("firstname");
                String ln = rs.getString("lastname");
                String p = rs.getString("patronymic");
                String b = rs.getString("birthday");
                char g = rs.getString("gender").charAt(0);

                System.out.println(id + "\t" + fn + "\t" + ln + "\t" + p + "\t" + b + "\t" + g);
            }

            rs.close();

            /////////////////////////////////////////////////

            st = c2.createStatement();
            ResultSet rs2 = st.executeQuery("SELECT id, typeid, people_id, series, number, date  FROM docs ORDER BY id");

            System.out.println(" ");
            while (rs2.next()) {
                int id = rs2.getInt("id");
                int ti = rs2.getInt("typeid");
                int pei = rs2.getInt("people_id");
                int ser = rs2.getInt("series");
                String n = rs2.getString("number");
                int docdata = rs2.getInt("date");

                System.out.println(id + "\t" + ti + "\t" + pei + "\t" + ser + "\t" + n + "\t" + docdata);
            }

            rs2.close();

        } catch (Exception ex) {
            System.out.println("ERROR [" + people_db_name + ".db or " + documents_db_name + ".db] select: ExecuteSelectError");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    void openDocumentsDb() {
        // Connection to documents_db_name
        try {
            Class.forName("org.sqlite.JDBC");
            c2 = DriverManager.getConnection("jdbc:sqlite:" + path_to_dbs + documents_db_name + ".db");
            System.out.println("DataBase opening (" + documents_db_name + ".db) was successful!");

        } catch (Exception ex) {
            System.out.println("ERROR [" + documents_db_name + ".db]");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    void insertPeople() {
        // TODO: обработка неправильного ввода

        System.out.print(new String("Имя: "));  // проверка (слово)
        String fn = sc.nextLine();

        System.out.print(new String("Фамилия: "));  // проверка (слово)
        String ln = sc.nextLine();

        System.out.print(new String("Отчество: "));  // проверка (слово)
        String p = sc.nextLine();

        System.out.print(new String("Дата рождения: "));  // проверка (дата)
        int b = sc.nextInt();

        System.out.print(new String("Ваш пол: "));  // проверка на 'M' и 'Ж',
        char s = (sc.nextLine().length() == 0 ? sc.nextLine() : sc.nextLine()).charAt(0);  // какая-то ошибка была исправлена ?: вместо sc.nextLine()

        /*/////////////*/// после удачного заполнения данных

        id_people++;

        insertDocuments(1); //паспорт
        insertDocuments(2); //снилс
        insertDocuments(3); //инн
        //insertDocuments(4); //вод. удост. нереализован
        if ("М".equals(s + "") || "м".equals(s + "")) {
            insertDocuments(5); //военный билет
        }

        String mess =
                "INSERT INTO " + people_db_statement + " (id, firstname, lastname, patronymic, birthday, gender) " +
                        "VALUES (" + id_people + ", '" + fn + "', '" + ln + "', '" + p + "', " + b + ", '" + s + "');";
        /*
        * INSERT INTO users (id, firstname, lastname, patronymic, birthday, gender)
        * VALUES (1, 'К', 'Айсберг', 'Провод', 312373, 'М');
        *
        * */

        try {
            st = c.createStatement();
            st.executeUpdate(mess);
            st.close();
        } catch (Exception ex) {

            System.out.println("ERROR [" + people_db_name + ".db] insert: ExecuteUpdateError");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    void insertDocuments(int a) {  // 1 - паспорт, 2 - снилс, 3 - инн, 4 - водительское удостоверение, 5 - военный билет
        id_docs++;

        String mess =
                "INSERT INTO " + documents_db_statement + " (id, typeid, people_id, series, number, date) ";
        int b1, b2, b3;
        if (a == 1) {
            System.out.print("Введите серию паспорта: ");
            b1 = sc.nextInt();
                /*if (("" + passS).length() != 4) {   проверка на 4 цифры
                    System.out.println("ошибка серии, нужно 4 цифры");
                }*/

            System.out.print("Введите номер паспорта: ");
            b2 = sc.nextInt();
                /*if (("" + passN).length() != 6) {   проверка на 6 цифр
                    System.out.println("ошибка серии, нужно 6 цифр");
                }*/

            System.out.print("Введите дату паспорта: ");
            b3 = sc.nextInt();
                /*if (("" + passDate).length() > 8 || ("" + passDate).length() < 7) {   проверка на 7-8 цифр
                    System.out.println("ошибка даты, нужно 7 цифр минимум");
                }*/

            mess = mess +
                    "VALUES (" + id_docs + ", 1, " + id_people + ", " + b1 + ", '" + b2 + "', " + b3 + ");";
        } else if (a == 2) {
            System.out.print("Введите снилс: ");
            b2 = sc.nextInt();
                /*if (("" + passS).length() != 4) {   проверка на 4 цифры
                    System.out.println("ошибка серии, нужно 4 цифры");
                }*/

            mess = mess +
                    "VALUES (" + id_docs + ", 2, " + id_people + ", NULL, '" + b2 + "', NULL);";
        } else if (a == 3) {
            System.out.print("Введите номер инн: ");
            b2 = sc.nextInt();
            //if (("" + passN).length() != 6) {   проверка на 6 цифр
            //     System.out.println("ошибка серии, нужно 6 цифр");
            // }

            System.out.print("Введите дату инн: ");
            b3 = sc.nextInt();
            /*if (("" + passDate).length() > 8 || ("" + passDate).length() < 7) {   проверка на 7-8 цифр
                System.out.println("ошибка даты, нужно 7 цифр минимум");
            }*/

            mess = mess +
                    "VALUES (" + id_docs + ", 3, " + id_people + ", NULL, '" + b2 + "', " + b3 + ");";
        } else if (a == 5) {
            System.out.print("Введите номер военного билета или приписное свидетельство: ");
            b2 = sc.nextInt();
            /*if (("" + passN).length() != 6) {   проверка на 6 цифр
                System.out.println("ошибка серии, нужно 6 цифр");
            }*/

            mess = mess +
                    "VALUES (" + id_docs + ", 5, " + id_people + ", NULL, '" + b2 + "', NULL);";
        }/* case 4: непонятно break; */

        try {
            st = c2.createStatement();
            st.executeUpdate(mess);
            st.close();

        } catch (Exception ex) {

            System.out.println("ERROR [" + documents_db_name + ".db] insertDocuments: ExecuteError");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }

    void close() {
        try {
            st.close();
            c.close();
            c2.close();
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            System.exit(0);
        }
    }
}