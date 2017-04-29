package assignment7.view;



import java.io.IOException;

import assignment7.ChatServer;
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
    private Text textbox;
    
    public void handleButt(){
        String usern= usernameField.getText();
        String passw = passwordField.getText();
        boolean check = Users.users.containsKey(usern);
        String pass = Users.users.get(usern);
        if(check) {
            if (pass.equals(passw)) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("assignment7/view/ChatGUI.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("My New Stage Title");
                    stage.setScene(new Scene(root, 450, 450));
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
       /* Parent root
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("assignment7/view/ChatGUI.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }

}