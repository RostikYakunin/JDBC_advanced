package Task3.DBConnect;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * class for creating connection to DB
 *
 * @author Yakunin
 * @version 2.0
 */

public class DBConnect {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/books_db";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Rost";

    private DBConnect() {
    }

    /**
     * Method for entering to DB after authorization or
     * registration in system
     *
     * @return connection to DB
     */
    public static Connection authorization() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Input your action ! \n" +
                "                \"1 - registration\" \n" +
                "                \"2 - connect to DB\"");
        int a = Integer.parseInt(inputReader());

        System.out.println("Input your login and password !" );

        switch (a) {
            case 1: registrationUsers(inputReader(),inputReader());
                break;
            case 2: successfulConnection(inputReader(), inputReader());
                break;
            default:
                System.err.println("Something wrong !");
        }
        return connection;
    }

    public static String inputReader() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        return s;
    }

    /**
     * Method for user`s registration on system
     * all user`s info add to file "users.txt"
     *
     * @param login new user`s login
     * @param password new user`s password
     */
    private static boolean registrationUsers(String login, String password) throws IOException {
        FileWriter fw = new FileWriter("users.txt", true);
        fw.write(login + "\n");
        fw.write(password + "\n");
        fw.close();
        return true;
    }

    /**
     * @param login from registration`s file
     * @param password from registration`s file
     * @return connection if user is authorized
     */

    private static Connection successfulConnection(String login, String password) throws IOException, SQLException, ClassNotFoundException {
        if (findUser(login, password)) {
            return getConnection();
        }
        return null;
    }

    /**
     * Method for validation user`s info
     */

    private static boolean findUser(String login, String password) throws IOException {
        List<String> list = Files.readAllLines(Paths.get("users.txt"));
        return list.contains(login) && list.contains(password);
    }

    /**
     * main method for connect to MySQL driver
     * @return connection
     */

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.err.println("Connection successful");
            return connection;
        }
        return connection;
    }

}
