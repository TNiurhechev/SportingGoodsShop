package com.example.demo1;

import db.Good;
import db.GoodDAO;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
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
    private Button logOutButton;

    @FXML
    private Label mainLabel;

    @FXML
    private TableColumn<Good, String> manufacturerColumn;

    @FXML
    private TableColumn<Good, String> modelColumn;

    @FXML
    private Button ordersButton;

    @FXML
    private ImageView productImage;

    @FXML
    private TableColumn<Good, Integer> priceColumn;

    @FXML
    private TableColumn<Good, Integer> sizeColumn;

    @FXML
    private TableColumn<Good, Integer> vendorCodeColumn;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label logLabel;

    public void setNickname(String nickname){
        nicknameLabel.setText(nickname);
    }

    @FXML
    void initialize() throws SQLException, IOException {

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

        //image
        Good first = l.get(0);
        Image prodImg = gd.getProductImage(first.getVendorCode());
        productImage.setImage(prodImg);
        productImage.setPreserveRatio(false);
        productImage.setVisible(true);

        //table selection listener
        ObservableList<Good> selectedItems =
                goodsTable.getSelectionModel().getSelectedItems();

        selectedItems.addListener(
                new ListChangeListener<Good>() {
                    @Override
                    public void onChanged(
                            Change<? extends Good> change) {
                        //int index = goodsTable.getSelectionModel().selectedIndexProperty().get();
                        Good selectedGood =  goodsTable.getSelectionModel().selectedItemProperty().get();
                        Image prodImg = null;
                        try {
                            prodImg = gd.getProductImage(selectedGood.getVendorCode());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        productImage.setImage(prodImg);
                        productImage.setPreserveRatio(false);
                        productImage.setVisible(true);
                    }
                });

        addAdminButton.setOnAction(event->{
            Stage primaryStage = (Stage)addAdminButton.getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add-admin.fxml"));
                Parent root = (Parent)loader.load();
                AddAdminController addAdminController = loader.getController();
                addAdminController.setNickname(nicknameLabel.getText());
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
        addProductButton.setOnAction(event->{
            Stage primaryStage = (Stage)addAdminButton.getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("add-product.fxml"));
                Parent root = (Parent)loader.load();
                AddProductController addProductController = loader.getController();
                addProductController.setNickname(nicknameLabel.getText());
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
        ordersButton.setOnAction(event->{
            Stage primaryStage = (Stage)ordersButton.getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-order.fxml"));
                Parent root = (Parent)loader.load();
                AdminOrderController adminOrderController = loader.getController();
                adminOrderController.setNickname(nicknameLabel.getText());
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

        logOutButton.setOnAction(event->{
            Stage primaryStage = (Stage)logOutButton.getScene().getWindow();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent root = (Parent)loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle(AppVersion.getCurrentVersion());
                stage.show();
                primaryStage.hide();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });
    }
}

