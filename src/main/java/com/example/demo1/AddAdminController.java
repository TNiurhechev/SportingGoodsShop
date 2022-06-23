package com.example.demo1;

import db.User;
import db.UserDAO;
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
import java.util.List;

public class AddAdminController {

    @FXML
    private Label appLabel;

    @FXML
    private Label copyrightLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Label mainLabel;

    @FXML
    private Label nicknameLabel;

    @FXML
    private TextField nicknameTextField;

    @FXML
    private Label password;

    @FXML
    private Label passwordConfirm;

    @FXML
    private PasswordField passwordConfirmField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    void initialize(){
        errorLabel.setVisible(false);
        signInButton.setOnAction(event->{
            UserDAO ud = new UserDAO();
            List<User> userList = ud.getAll();
            boolean check = false;
            for(User u:userList){
                if(u.getNickname().equals(nicknameTextField.getText())) {
                    errorLabel.setText("This nickname is taken!");
                    errorLabel.setVisible(true);
                    check = true;
                }
            }
            if(!passwordField.getText().equals(passwordConfirmField.getText())){
                errorLabel.setVisible(true);
                check = true;
            }
            if(check==false) {
                try {
                    ud.save(new User(nicknameTextField.getText(),passwordField.getText(),1));
                    Stage primaryStage = (Stage)signInButton.getScene().getWindow();
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
        });
    }
}
