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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class UserOrderController {

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressTextField;

    @FXML
    private Label appLabel;

    @FXML
    private Label copyrightLabel;

    @FXML
    private Label imageLabel;

    @FXML
    private Label mainLabel;

    @FXML
    private Label manufacturerLabel;

    @FXML
    private Label manufacturerValueLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label modelValueLabel;

    @FXML
    private Button orderButton;

    @FXML
    private Label priceLabel;

    @FXML
    private Label priceValueLabel;

    @FXML
    private ImageView productImage;

    @FXML
    private AnchorPane productPane;

    @FXML
    private Button returnButton;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label sizeValueLabel;

    @FXML
    private Label vendorCodeLabel;

    @FXML
    private Label vendorCodeValueLabel;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label logLabel;

    private Good selectedGood;

    private Image selectedImage;

    public void setNickname(String nickname){
        nicknameLabel.setText(nickname);
    }

    public void setSelectedGood(int vendorCode, String manufacturer, String model, int size, int price){
        selectedGood = new Good(vendorCode, manufacturer, model, size, price);
        this.vendorCodeValueLabel.setText(String.valueOf(selectedGood.getVendorCode()));
        this.manufacturerValueLabel.setText(selectedGood.getManufacturer());
        this.modelValueLabel.setText(selectedGood.getModel());
        this.sizeValueLabel.setText(String.valueOf(selectedGood.getSize()));
        this.priceValueLabel.setText(String.valueOf(selectedGood.getPrice()));
    }

    public void setSelectedImage(Image image) {
        this.selectedImage = image;
        this.productImage.setImage(selectedImage);
    }

    @FXML
    void initialize() throws SQLException {

        orderButton.setOnAction(event->{
            OrderDao od = new OrderDao();
            try {
                od.save(new Order(nicknameLabel.getText(),addressTextField.getText(), selectedGood.getVendorCode(),
                        selectedGood.getPrice()));
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
