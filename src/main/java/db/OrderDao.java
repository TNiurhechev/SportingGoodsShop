package db;

import javafx.scene.image.Image;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements DAO{

    private Connection getConnection(){
        DBManager dbManager = new DBManager("localhost:3306","root","12345678","shoppingmanager");
        return dbManager.connect();
    }

    @Override
    public Order get(long id) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Orders("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Customer VARCHAR(100),"
                    + "Address VARCHAR(100),"
                    + "VendorCode INTEGER, Price INTEGER);");
            ResultSet ordersSet = statement.executeQuery("SELECT * FROM Orders;");

            while(ordersSet.next()) {
                int orderId = ordersSet.getInt("Id");
                String customer = ordersSet.getString("Customer");
                String address = ordersSet.getString("Address");
                int vendorCode = ordersSet.getInt("VendorCode");
                int price = ordersSet.getInt("Price");
                Order ord = new Order(orderId,customer,address,vendorCode,price);
                if(orderId==id)
                    return ord;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Orders("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Customer VARCHAR(100),"
                    + "Address VARCHAR(100),"
                    + "VendorCode INTEGER, Price INTEGER);");
            ResultSet ordersSet = statement.executeQuery("SELECT * FROM Orders;");
            List<Order> orders = new ArrayList<Order>();
            while(ordersSet.next()) {
                int orderId = ordersSet.getInt("Id");
                String customer = ordersSet.getString("Customer");
                String address = ordersSet.getString("Address");
                int vendorCode = ordersSet.getInt("VendorCode");
                int price = ordersSet.getInt("Price");
                orders.add(new Order(orderId,customer,address,vendorCode,price));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Object o) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Orders("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Customer VARCHAR(100),"
                    + "Address VARCHAR(100),"
                    + "VendorCode INTEGER, Price INTEGER);");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        Order ord = (Order)o;
        PreparedStatement insertOrder = connection.prepareStatement("INSERT INTO " +
                "Orders(Customer,Address,VendorCode,Price) VALUES (?,?,?,?)");
        insertOrder.setString(1,ord.getCustomer());
        insertOrder.setString(2,ord.getAddress());
        insertOrder.setInt(3,ord.getVendorCode());
        insertOrder.setInt(4,ord.getPrice());
        insertOrder.executeUpdate();
    }

    @Override
    public void update(Object o, String[] params) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Orders("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Customer VARCHAR(100),"
                    + "Address VARCHAR(100),"
                    + "VendorCode INTEGER, Price INTEGER);");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        Order ord = (Order) o;
        //TODO
    }

    @Override
    public void delete(Object o) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Orders("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "Customer VARCHAR(100),"
                    + "Address VARCHAR(100),"
                    + "VendorCode INTEGER, Price INTEGER);");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        Order ord = (Order)o;
        PreparedStatement deleteOrder = connection.prepareStatement("DELETE FROM Orders" +
                " WHERE Id=?");
        deleteOrder.setInt(1,ord.getId());
        deleteOrder.executeUpdate();
    }
}
