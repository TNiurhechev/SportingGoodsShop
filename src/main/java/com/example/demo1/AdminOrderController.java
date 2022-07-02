package com.example.demo1;

import db.Good;
import db.GoodDAO;
import db.Order;
import db.OrderDao;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TableColumn<Order, Integer> idColumn;

    @FXML
    private Label mainLabel;

    @FXML
    private Label orderInfoLabel;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Integer> priceColumn;

    @FXML
    private Button removeOrderButton;

    @FXML
    private Button returnButton;

    @FXML
    private TableColumn<Order, Integer> vendorCodeColumn;

    private Order selectedOrder;

    @FXML
    void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        vendorCodeColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("vendorCode"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("customer"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("price"));
        OrderDao od = new OrderDao();
        List<Order> l = od.getAll();
        selectedOrder = l.get(0);
        ObservableList<Order> ol = FXCollections.observableArrayList();
        for(Order o:l)
            ol.add(o);
        ordersTable.setItems(ol);

        //action on stage close
        /*Stage ps = (Stage)removeOrderButton.getScene().getWindow();
        ps.setOnCloseRequest(event->{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-main.fxml"));
                Parent root = (Parent)loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                ps.hide();

            catch(Exception ex){
                ex.printStackTrace();
            }
        });*/

        returnButton.setOnAction(event->{
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
        });

        ObservableList<Order> selectedItems =
                ordersTable.getSelectionModel().getSelectedItems();

        selectedItems.addListener(
                new ListChangeListener<Order>() {
                    @Override
                    public void onChanged(
                            Change<? extends Order> change) {
                        //int index = goodsTable.getSelectionModel().selectedIndexProperty().get();
                        selectedOrder = ordersTable.getSelectionModel().selectedItemProperty().get();

                    }
                });

        removeOrderButton.setOnAction(event->{

                    try {
                        od.delete(selectedOrder);
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
        });
    }
}

