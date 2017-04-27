package assignment7.view;

import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.awt.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;


public class Chat {

    // testing for second commit
    @FXML
    private AnchorPane mainpane;

    @FXML
    private TabPane tabpane;

    @FXML
    private Tab tab1;

    @FXML
    private TextArea output;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private SplitPane split1;

    @FXML
    private AnchorPane anchor1a;

    @FXML
    private TextArea input;

    @FXML
    private AnchorPane anchor1b;

    @FXML
    private Button sender;

    @FXML
    private Button hellobutton;

    private BufferedReader reader;

    private PrintWriter writer;

    public static Socket sock;
    @FXML
    public void initialize() throws Exception {
        setUpNetworking();
    }


    private void setUpNetworking() throws Exception {
        @SuppressWarnings("resource")
        Client x = new Client("hello" , " Cool ", 4242);
        InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
        reader = new BufferedReader(streamReader);
        writer = new PrintWriter(sock.getOutputStream());
        System.out.println("networking established");
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }

       public void sendtext() {
            writer.println(output.getText());
            writer.flush();
            output.setText("");
            output.requestFocus();
        }


    class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {

                    input.appendText(message + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
