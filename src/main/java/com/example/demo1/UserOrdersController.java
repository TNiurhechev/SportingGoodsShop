package com.example.demo1;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class UserOrdersController {

    @FXML
    private Label appLabel;

    @FXML
    private Button cancelOrderButton;

    @FXML
    private Label copyrightLabel;

    @FXML
    private TableColumn<Order, String> customerColumn;

    @FXML
    private TableColumn<Order, Integer> idColumn;

    @FXML
    private Label infoLabel;

    @FXML
    private Label logLabel;

    @FXML
    private Label mainLabel;

    @FXML
    private Label nicknameLabel;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, Integer> priceColumn;

    @FXML
    private TableColumn<Order, String> addressColumn;

    @FXML
    private Button returnButton;

    @FXML
    private TableColumn<Order, Integer> vendorCodeColumn;

    private Order selectedOrder;

    public void setNickname(String nickname){
        nicknameLabel.setText(nickname);
        OrderDao od = new OrderDao();
        idColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        vendorCodeColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("vendorCode"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("customer"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("address"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("price"));
        List<Order> l = od.getAll();
        selectedOrder = l.get(0);
        ObservableList<Order> ol = FXCollections.observableArrayList();
        for(Order o:l) {
            if(o.getCustomer().equals(nicknameLabel.getText())){
                ol.add(o);
            }
        }
        ordersTable.setItems(ol);
    }

    @FXML
    public void initialize(){
        OrderDao od = new OrderDao();

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

        cancelOrderButton.setOnAction(event->{

            try {
                od.delete(selectedOrder);
                Stage primaryStage = (Stage)cancelOrderButton.getScene().getWindow();
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("user-main.fxml"));
                    Parent root = (Parent)loader.load();
                    UserMainController userMainController = loader.getController();
                    userMainController.setNickname(nicknameLabel.getText());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle(primaryStage.getTitle());
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

        returnButton.setOnAction(event->{
            Stage primaryStage = (Stage)returnButton.getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("user-main.fxml"));
                Parent root = (Parent)loader.load();
                UserMainController userMainController = loader.getController();
                userMainController.setNickname(nicknameLabel.getText());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle(primaryStage.getTitle());
                stage.show();
                primaryStage.hide();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
}
