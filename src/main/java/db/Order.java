package db;

public class Order {
    private String customer;
    private int vendorCode;
    private int price;

    public Order(String customer, int vendorCode, int price){
        this.customer = customer;
        this.vendorCode = vendorCode;
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }

    public int getVendorCode() {
        return vendorCode;
    }

    public int getPrice() {
        return price;
    }
}
