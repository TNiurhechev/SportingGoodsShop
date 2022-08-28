package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User>{

    private Connection getConnection(){
        DBManager dbManager = new DBManager("localhost:3306","root","12345678","shoppingmanager");
        return dbManager.connect();
    }

    @Override
    public User get(long id) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Users("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Nickname VARCHAR(100),"
                    + "Password VARCHAR(100), IsAdmin INTEGER;");
            ResultSet usersSet = statement.executeQuery("SELECT * FROM Users;");

            while(usersSet.next()) {
                int userId = usersSet.getInt("Id");
                String nickname = usersSet.getString("Nickname");
                String password = usersSet.getString("Password");
                int isAdmin = usersSet.getInt("IsAdmin");
                User u = new User(nickname,password,isAdmin);
                if(userId==id)
                    return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List getAll() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Users("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Nickname VARCHAR(100),"
                    + "Password VARCHAR(100), IsAdmin INTEGER);");
            ResultSet usersSet = statement.executeQuery("SELECT * FROM Users;");
            List<User> users = new ArrayList<User>();
            while(usersSet.next()) {
                int userId = usersSet.getInt("Id");
                String nickname = usersSet.getString("Nickname");
                String password = usersSet.getString("Password");
                int isAdmin = usersSet.getInt("IsAdmin");
                users.add(new User(nickname,password,isAdmin));
            }
            return users;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(User u) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Users("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Nickname VARCHAR(100),"
                    + "Password VARCHAR(100), IsAdmin INTEGER);");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        PreparedStatement insertUser = connection.prepareStatement("INSERT INTO " +
                "Users(Nickname,Password,isAdmin) VALUES (?,?,?)");
        insertUser.setString(1,u.getNickname());
        insertUser.setString(2,u.getPassword());
        insertUser.setInt(3,u.getIsAdmin());
        insertUser.executeUpdate();
    }

    @Override
    public void update(User u, String[] params) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Users("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Nickname VARCHAR(100),"
                    + "Password VARCHAR(100), IsAdmin INTEGER);");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        //TODO
    }

    @Override
    public void delete(User u) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Users("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Nickname VARCHAR(100),"
                    + "Password VARCHAR(100), IsAdmin INTEGER);");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM Users" +
                " WHERE Nickname=?");
        deleteUser.setString(1,u.getNickname());
        deleteUser.executeUpdate();
    }
}
