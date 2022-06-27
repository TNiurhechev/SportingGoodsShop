package db;

import com.example.demo1.UserMainController;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoodDAO implements DAO{

    public GoodDAO(){
    }

    private Connection getConnection(){
        DBManager dbManager = new DBManager("localhost:3306","root","12345678","shoppingmanager");
        return dbManager.connect();
    }

    @Override
    public Good get(long id) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Goods("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "VendorCode INTEGER, Manufacturer VARCHAR(100),"
                    + "Model VARCHAR(100), Size INTEGER, "
                    + "Price INTEGER, Image BLOB);");
            ResultSet goodsSet = statement.executeQuery("SELECT * FROM Goods;");

            while(goodsSet.next()) {
                int goodId = goodsSet.getInt("Id");
                int vendorCode = goodsSet.getInt("VendorCode");
                String manufacturer = goodsSet.getString("Manufacturer");
                String model = goodsSet.getString("Model");
                int size = goodsSet.getInt("Size");
                int price = goodsSet.getInt("Price");
                Good g = new Good(vendorCode,manufacturer,model,size,price,null);
                if(goodId==id)
                    return g;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Good> getAll() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Goods("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "VendorCode INTEGER, Manufacturer VARCHAR(100),"
                    + "Model VARCHAR(100), Size INTEGER, "
                    + "Price INTEGER, Image BLOB);");
            ResultSet goodsSet = statement.executeQuery("SELECT * FROM Goods;");
            List<Good> goods = new ArrayList<Good>();
            while(goodsSet.next()) {
                int goodId = goodsSet.getInt("Id");
                int vendorCode = goodsSet.getInt("VendorCode");
                String manufacturer = goodsSet.getString("Manufacturer");
                String model = goodsSet.getString("Model");
                int size = goodsSet.getInt("Size");
                int price = goodsSet.getInt("Price");
                goods.add(new Good(vendorCode,manufacturer,model,size,price,null));
            }
            return goods;
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
            statement.execute("CREATE TABLE if NOT EXISTS Goods("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "VendorCode INTEGER, Manufacturer VARCHAR(100),"
                    + "Model VARCHAR(100), Size INTEGER, "
                    + "Price INTEGER, Image BLOB);");

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        Good g = (Good)o;
        PreparedStatement insertGood = connection.prepareStatement("INSERT INTO " +
                "Goods(VendorCode,Manufacturer,Model,Size,Price,Image) VALUES (?,?,?,?,?,?)");
        insertGood.setInt(1,g.getVendorCode());
        insertGood.setString(2,g.getManufacturer());
        insertGood.setString(3,g.getModel());
        insertGood.setInt(4,g.getSize());
        insertGood.setInt(5,g.getPrice());
        insertGood.setBinaryStream(6, g.getImage());
        insertGood.executeUpdate();
    }

    @Override
    public void update(Object o, String[] params) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Goods("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "VendorCode INTEGER, Manufacturer VARCHAR(100),"
                    + "Model VARCHAR(100), Size INTEGER, "
                    + "Price INTEGER, Image BLOB);");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Good g = (Good)o;
        //TODO
    }

    @Override
    public void delete(Object o) throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Goods("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "VendorCode INTEGER, Manufacturer VARCHAR(100),"
                    + "Model VARCHAR(100), Size INTEGER, "
                    + "Price INTEGER, Image BLOB);");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Good g = (Good) o;
        PreparedStatement deleteGood = connection.prepareStatement("DELETE FROM Goods" +
                " WHERE VendorCode=?");
        deleteGood.setInt(1,g.getVendorCode());
        deleteGood.executeUpdate();
    }

    public Image getProductImage(int vendorCode) throws SQLException, IOException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS Goods("
                    + "Id INTEGER PRIMARY KEY auto_increment, "
                    + "VendorCode INTEGER, Manufacturer VARCHAR(100),"
                    + "Model VARCHAR(100), Size INTEGER, "
                    + "Price INTEGER, Image BLOB);");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement getImage = connection.prepareStatement("SELECT Image from Goods WHERE VendorCode=?");
        getImage.setInt(1,vendorCode);
        ResultSet rs = getImage.executeQuery();
        if(rs.next()){
            InputStream is = rs.getBinaryStream(1);
            OutputStream os = new FileOutputStream(new File("photo.jpg"));
            byte[] contents = new byte[2048];
            int size = 0;
            while((size = is.read(contents))!=-1){
                os.write(contents,0,size);
            }
            os.close();
            is.close();
            Image img = new Image("file:photo.jpg",200,150,true,true);
            return img;
        }
        throw new IOException("");
    }
}
