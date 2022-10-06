package Task1.autorization;

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
 * @author Yakunin
 * @version 1.0
 *
 * Class for creating connection to DB after authorization
 *
 */

public class DBConnect {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Rost";

    private DBConnect() {
    }


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection successful");
            return connection;
        }
        return connection;
    }

    private void authorization() throws IOException, SQLException, ClassNotFoundException {
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
    }

    private boolean findUser(String login, String password) throws IOException {
        List<String> list = Files.readAllLines(Paths.get("users.txt"));
        return list.contains(login) && list.contains(password);
    }

    private boolean registrationUsers(String login, String password) throws IOException {
        FileWriter fw = new FileWriter("users.txt", true);
        fw.write(login + "\n");
        fw.write(password + "\n");
        fw.close();
        return true;
    }

    private String inputReader() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        return s;
    }

    private void successfulConnection (String login, String password) throws IOException, SQLException, ClassNotFoundException {
        if (findUser(login, password)) {
            Connection connection = DBConnect.getConnection();
        }
    }
}
