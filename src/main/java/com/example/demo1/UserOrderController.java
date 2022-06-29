package com.example.demo1;

import db.Good;
import db.GoodDAO;
import db.Order;
import db.OrderDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class UserOrderController {

    @FXML
    private Label appLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private Label copyrightLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Label mainLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private Label vendorCodeLabel;

    @FXML
    private ListView<Integer> vendorCodeListView;

    private Integer[] vendorCodes;

    @FXML
    void initialize() throws SQLException {
        errorLabel.setVisible(false);
        GoodDAO gd = new GoodDAO();
        List<Good> goods = gd.getAll();
        vendorCodes = new Integer[goods.size()];
        int index = 0;
        for(Good g:goods){
            vendorCodes[index] = g.getVendorCode();
            index++;
        }
        vendorCodeListView.getItems().addAll(vendorCodes);
        confirmButton.setOnAction(event->{
            boolean check = false;
            for(Good g:goods){
                if(g.getVendorCode()==Integer.parseInt(vendorCodeListView.getSelectionModel().getSelectedItem().toString())){
                    Good selected = g;
                    check = true;
                    OrderDao od = new OrderDao();
                    try {
                        od.save(new Order(nameField.getText(), selected.getVendorCode(), selected.getPrice()));
                        Stage primaryStage = (Stage)confirmButton.getScene().getWindow();
                        try{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("user-main.fxml"));
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
            if(check==false)
                errorLabel.setVisible(true);
        });
    }

}