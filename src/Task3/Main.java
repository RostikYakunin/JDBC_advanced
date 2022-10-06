package Task3;

import Task2.entity.Abonent;
import Task3.DAO.AbonentDAO;
import Task3.DBConnect.DBConnect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DBConnect.getConnection()) {
            AbonentDAO abonentDAO = new AbonentDAO();
//            abonentDAO.updateAbonentById(2,connection);
//            System.out.println(getAbonentById(4, connection));


            System.out.println(abonentDAO.countAllEntries(connection));
            System.out.println("abonentDAO.countAllEntriesAfterTwo(connection) = " + abonentDAO.countAllEntriesAfterTwo(connection));

        }
    }



}
