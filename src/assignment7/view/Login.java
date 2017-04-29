package assignment7.view;



import java.io.IOException;

import assignment7.ChatServer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.util.*;


public class Login {


    @FXML
    private Text textbox;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button newAccountButt;

    @FXML
    private Button logInButt;

    @FXML
    private TextFlow loginText;

    @FXML
    private Button test;

    @FXML
    private Button priv;

    @FXML
    private Button group;


    public void handleButt(){
        String usern= usernameField.getText();
        String passw = passwordField.getText();
        boolean check = Users.users.containsKey(usern);
        String pass = Users.users.get(usern);

        if(check) {
            if (pass.equals(passw)) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("assignment7/view/GroupOrPrivate.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("What Type of Chat");
                    stage.setScene(new Scene(root, 500, 500));
                    stage.show();


                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            textbox.setText("Incorrect Password!");
        }
    else
        {
            textbox.setText("Username Doesn't Exist!");
        }

    }
    public void newAccount()
    {
        textbox.setText("Enter New UserName and Password");
        String usern= usernameField.getText();
        String passw = passwordField.getText();
        if(Users.users.containsKey(usern))
        {
            textbox.setText("Username Already Exists");
        }
        else
        {
            Users.users.put(usern, passw);
        }
        handleButt();
    }








    public void handleButtG(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ChatWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Group Chat");
            stage.setScene(new Scene(root, 500, 500));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handleButtP(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ChatWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Private Chat");
            stage.setScene(new Scene(root, 500, 500));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}