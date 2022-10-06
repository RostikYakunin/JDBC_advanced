package Task3.DAO;

import Task2.entity.Abonent;
import Task3.DBConnect.DBConnect;

import java.io.IOException;
import java.sql.*;

public class AbonentDAO  {

    public AbonentDAO () {

    }

    public Abonent getAbonentById (int id, Connection connection) throws SQLException {
        String sql = "SELECT * FROM books_db.phonebook WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        Abonent abonent = null;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString(2);
            int tel = resultSet.getInt(3);
            abonent = new Abonent(id,name,tel);
        }
        return abonent;
    }

    public boolean updateAbonentById (int id, Connection connection) throws SQLException, IOException {
        String sql = "UPDATE books_db.phonebook SET lastName = ?, phone = ? WHERE id =" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        System.out.println("Введите новую фамилию");
        preparedStatement.setString(1, DBConnect.inputReader());
        System.out.println("Введите новый телефон");
        preparedStatement.setInt(2, Integer.parseInt(DBConnect.inputReader()));

        int result = preparedStatement.executeUpdate();
        return result==1;
    }

    public int countAllEntries (Connection connection) throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("{CALL phoneBookCount(?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();

        return callableStatement.getInt(1);
    }

    public int countAllEntriesAfterTwo (Connection connection) throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("{CALL countAllAfterTwo(?)}");
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();

        return callableStatement.getInt(1);
    }
}
