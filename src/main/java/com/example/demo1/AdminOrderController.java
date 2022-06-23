package com.example.demo1;

import db.Good;
import db.GoodDAO;
import db.Order;
import db.OrderDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class AdminOrderController {

    @FXML
    private Label appLabel;

    @FXML
    private Label copyrightLabel;

    @FXML
    private TableColumn<Order, String> customerColumn;

    @FXML
    private Label customerLabel;

    @FXML
    private TextField customerTextField;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private Label mainLabel;

    @FXML
    private TableColumn<Order, Integer> priceColumn;

    @FXML
    private Button removeOrderButton;

    @FXML
    private TableColumn<Order, Integer> vendorCodeColumn;

    @FXML
    void initialize(){
        vendorCodeColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("vendorCode"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("customer"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("price"));
        OrderDao od = new OrderDao();
        List<Order> l = od.getAll();
        ObservableList<Order> ol = FXCollections.observableArrayList();
        for(Order o:l)
            ol.add(o);
        ordersTable.setItems(ol);
        removeOrderButton.setOnAction(event->{
            for(Order o:l){
                if(o.getCustomer().equals(customerTextField.getText())) {
                    try {
                        od.delete(o);
                        Stage primaryStage = (Stage)removeOrderButton.getScene().getWindow();
                        try{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-main.fxml"));
                            Parent root = (Parent)loader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                            primaryStage.hide();
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}

