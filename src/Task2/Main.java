package Task2;

import Task2.DBConnect.DBConnect;
import Task2.entity.Abonent;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("getAllAbonents() = " + getAllAbonents());

        List <Abonent> list = getAllAbonents();

        for (Abonent abonent:list) {
            if (abonent.getId()>3) System.out.println(abonent);
        }

    }

    private static List<Abonent> getAllAbonents() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DBConnect.getConnection();

        List<Abonent> listOfAbonents = new ArrayList<>();
        String sql = "SELECT * FROM phonebook";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String lastname = resultSet.getString(2);
                int telephone = resultSet.getInt(3);

                Abonent abonent = new Abonent(id, lastname, telephone);
                listOfAbonents.add(abonent);
            }
        }

        return listOfAbonents;
    }


}
