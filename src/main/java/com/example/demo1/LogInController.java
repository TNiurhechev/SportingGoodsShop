package com.example.demo1;

import db.User;
import db.UserDAO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.util.List;

public class LogInController {

    @FXML
    private Label appLabel;

    @FXML
    private Label copyrightLabel;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private Label mainLabel;

    @FXML
    private Label nicknameLabel;

    @FXML
    private TextField nicknameTextField;

    @FXML
    private PasswordField password;

    @FXML
    private Button signInButton;
    @FXML
    void initialize(){
        loginErrorLabel.setVisible(false);
        signInButton.setOnAction(event->{
            UserDAO ud = new UserDAO();
            List<User> users = ud.getAll();
            for(User u:users) {
                if (u.getNickname().equals(nicknameTextField.getText())) {
                    if (u.getPassword().equals(password.getText())) {
                        Stage primaryStage = (Stage)signInButton.getScene().getWindow();
                        if (u.getIsAdmin() == 1) {
                            try{
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-main.fxml"));
                                Parent root = (Parent)loader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.setTitle("Logged in as "+this.nicknameTextField.getText()+" | AW Shop Manager 1.2.2");
                                stage.show();
                                primaryStage.hide();
                            }
                            catch(Exception ex){
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("user-main.fxml"));
                                Parent root = (Parent) loader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.setTitle("Logged in as "+this.nicknameTextField.getText()+" | AW Shop Manager 1.2.2");
                                stage.show();
                                primaryStage.hide();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
            loginErrorLabel.setVisible(true);
        });

        }

}


