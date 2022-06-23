package db;

import javafx.scene.image.Image;

import java.sql.Blob;

public class Good {
    int vendorCode;
    String manufacturer;
    String model;
    int size;
    int price;
    Blob image;//BLOB

    public Good(int vendorCode, String manufacturer, String model, int size, int price, Blob image) {
        this.vendorCode = vendorCode;
        this.manufacturer = manufacturer;
        this.model = model;
        this.size = size;
        this.price  = price;
        this.image = image;
    }

    public Good(int vendorCode, String manufacturer, String model, int size, int price) {
        this.vendorCode = vendorCode;
        this.manufacturer = manufacturer;
        this.model = model;
        this.size = size;
        this.price  = price;
        this.image = null;
    }

    public int getVendorCode() {
        return vendorCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public Blob getImage() {
        return image;
    }
}
