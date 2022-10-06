package Task1.autorization;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
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

    private static boolean findUser(String login, String password) throws IOException {
        List<String> list = Files.readAllLines(Paths.get("users.txt"));
        return list.contains(login) && list.contains(password);
    }

    private static boolean registrationUsers(String login, String password) throws IOException {
        FileWriter fw = new FileWriter("users.txt", true);
        fw.write(login + "\n");
        fw.write(password + "\n");
        fw.close();
        return true;
    }

    private static String inputReader() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        return s;
    }

    private static void successfulConnection (String login, String password) throws IOException, SQLException, ClassNotFoundException {
        if (findUser(login, password)) {
           Connection connection = DBConnect.getConnection();
        }
    }
}