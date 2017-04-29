package assignment7.view;



import java.io.IOException;

import assignment7.ChatServer;
import assignment7.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.util.*;



public class Login {

    @FXML
    private TextFlow textbox;

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
    private Button select;

    @FXML
    private Button priv;

    @FXML
    private Button group;
    

    private ArrayList<String> loggedin = new ArrayList<String>();

    
    public void handleButt(){
    	
        String usern= usernameField.getText();
        String passw = passwordField.getText();
        boolean check = Users.users.containsKey(usern);
        String pass = Users.users.get(usern);

        if(check) {
        	if(loggedin.contains(usern)){
        		textbox.getChildren().clear();
                textbox.getChildren().add(new Text("Username already logged in"));
        	}
        	else if (pass.equals(passw)) {
            	loggedin.add(usern);
            	textbox.getChildren().clear();
            	usernameField.clear();
            	passwordField.clear();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("assignment7/view/GroupOrPrivate.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("What Type of Chat");
                    stage.setScene(new Scene(root, 320, 240));
                    stage.show();


                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            else{
            textbox.getChildren().clear();
            textbox.getChildren().add(new Text("Incorrect Password!"));
            }
        }
    else
        {
    		textbox.getChildren().clear();
    		textbox.getChildren().add(new Text("Username Doesn't Exist!"));
        }

    }
    public void register()
    {
    	 Parent root;
         try {
             root = FXMLLoader.load(getClass().getClassLoader().getResource("assignment7/view/CreateAccount.fxml"));
             Stage stage = new Stage();
             stage.setTitle("What Type of Chat");
             stage.setScene(new Scene(root, 600, 400));
             stage.show();


             // Hide this current window (if this is what you want)
             //((Node)(event.getSource())).getScene().getWindow().hide();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
/*    	textbox.getChildren().clear();
    	textbox.getChildren().add(new Text("Enter New UserName and Password"));*/
        
       // handleButt();
    }

    public void newAccount(){
    	 String usern= usernameField.getText();
         String passw = passwordField.getText();
         if(Users.users.containsKey(usern))
         {
         	textbox.getChildren().clear();
         	textbox.getChildren().add(new Text("Username Already Exists"));
         }
         else
         {
             Users.users.put(usern, passw);
         }
         Stage stage = (Stage) textbox.getScene().getWindow();
         stage.close();
    }

    public void handleButtG(){
    	Stage close = (Stage) group.getScene().getWindow();
        close.close();
        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("GroupChatWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Group Chat");
            stage.setScene(new Scene(root, 631, 500));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handleButtP(){
    	Stage close = (Stage) priv.getScene().getWindow();
        close.close();

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ChatWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Private Chat");
            stage.setScene(new Scene(root, 631, 500));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}