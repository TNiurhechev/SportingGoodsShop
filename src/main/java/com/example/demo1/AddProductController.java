package com.example.demo1;

import db.Good;
import db.GoodDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddProductController {

    @FXML
    private Button addProductButton;

    @FXML
    private Label appLabel;

    @FXML
    private Label copyrightLabel;

    @FXML
    private Label mainLabel;

    @FXML
    private Label manufacturerLabel;

    @FXML
    private TextField manufacturerTextField;

    @FXML
    private Label modelLabel;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private Label sizeLabel;

    @FXML
    private TextField sizeTextField;

    @FXML
    private Label vendorCodeLabel;

    @FXML
    private TextField vendorCodeTextField;

    @FXML
    void initialize(){
        addProductButton.setOnAction(event->{
            GoodDAO gd = new GoodDAO();
            try {
                gd.save(new Good(Integer.parseInt(vendorCodeTextField.getText()),manufacturerTextField.getText(),
                        modelTextField.getText(),Integer.parseInt(sizeTextField.getText()),Integer.parseInt(vendorCodeTextField.getText())));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Stage primaryStage = (Stage)addProductButton.getScene().getWindow();
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
    }
}

