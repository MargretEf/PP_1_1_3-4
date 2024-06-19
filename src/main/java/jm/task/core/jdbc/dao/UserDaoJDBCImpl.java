package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
   Connection conn = getConnection();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
 String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users (Id INT AUTO_INCREMENT PRIMARY KEY , Name VARCHAR(20) NOT NULL,LastName VARCHAR(20) NOT NULL, Age SMALLINT NOT NULL);";
try(Statement statement = conn.createStatement()){
    statement.executeUpdate(createUsersTableSQL);

} catch (SQLException e) {
    e.printStackTrace();
}

    }

    public void dropUsersTable() {
String dropUsersTableSQL = "DROP TABLE IF EXISTS users;";
        try(Statement statement = conn.createStatement()){
            statement.executeUpdate(dropUsersTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (Name, LastName, Age) VALUES (?,?,?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + name +" добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void removeUserById(long id) {
String sql = "DELETE FROM users WHERE Id = id";
            try(Statement statement = conn.createStatement();){
                statement.executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<User>();
        try(Statement statement = conn.createStatement() ; ResultSet resultSet = statement.executeQuery(sql) ) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
String sql = "DELETE FROM users;";
        try(Statement statement = conn.createStatement()){
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
