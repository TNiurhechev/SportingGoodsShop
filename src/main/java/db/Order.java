package db;

public class Order {
    private int id;
    private String customer;
    private String address;
    private int vendorCode;
    private int price;

    public Order(int id, String customer, String address, int vendorCode, int price){
        this.id = id;
        this.customer = customer;
        this.address = address;
        this.vendorCode = vendorCode;
        this.price = price;
    }

    public Order(String customer, String address, int vendorCode, int price){
        this.id = 0;
        this.customer = customer;
        this.address = address;
        this.vendorCode = vendorCode;
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }

    public int getVendorCode() {
        return vendorCode;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
