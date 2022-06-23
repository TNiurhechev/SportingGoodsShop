package com.example.demo1;

import db.Good;
import db.GoodDAO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class AdminMainController {

    @FXML
    private Button addAdminButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Label appLabel;

    @FXML
    private Label copyrightLabel;

    @FXML
    private TableView<Good> goodsTable;

    @FXML
    private Label mainLabel;

    @FXML
    private TableColumn<Good, String> manufacturerColumn;

    @FXML
    private TableColumn<Good, String> modelColumn;

    @FXML
    private Button ordersButton;

    @FXML
    private TableColumn<Good, Integer> priceColumn;

    @FXML
    private TableColumn<Good, Integer> sizeColumn;

    @FXML
    private TableColumn<Good, Integer> vendorCodeColumn;

    @FXML
    void initialize(){
        vendorCodeColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("vendorCode"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("manufacturer"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Good, String>("model"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("size"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Good, Integer>("price"));
        GoodDAO gd = new GoodDAO();
        List<Good> l = gd.getAll();
        ObservableList<Good> ol = FXCollections.observableArrayList();
        for(Good g:l)
            ol.add(g);
        goodsTable.setItems(ol);
        addAdminButton.setOnAction(event->{
            Stage primaryStage = (Stage)addAdminButton.getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add-admin.fxml"));
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
        addProductButton.setOnAction(event->{
            Stage primaryStage = (Stage)addAdminButton.getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add-product.fxml"));
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
        ordersButton.setOnAction(event->{
            Stage primaryStage = (Stage)ordersButton.getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-order.fxml"));
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
    }

}

