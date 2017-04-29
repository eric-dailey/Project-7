package assignment7.view;

import java.io.IOException;
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


public class Login {

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
    
    public void handleButt1(){
    	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GroupOrPrivate.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void handleButtG(){
    	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ChatWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
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
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}