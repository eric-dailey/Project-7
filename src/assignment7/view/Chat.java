package assignment7.view;

import java.util.*;

import assignment7.ChatServer;
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
    private TextArea output;


    @FXML
    private TextArea input;

    @FXML
    private TextArea groupoutput;

    @FXML
    private TextArea groupinput;


    private BufferedReader reader;

    private PrintWriter writer;

    public static Socket sock;

    public Client user;

    private BufferedReader groupreader;

    private PrintWriter groupwriter;

    public static Socket groupsock;

    public  String username;

    public  String passes;

    static int count = 0;

    private int ID;


    @FXML
    public void initialize() throws Exception {
        ChatServer.users.add(ChatServer.userList.get(Integer.valueOf(ID)));
        setUpNetworking();

    }

    private void setUpNetworking() throws Exception {
        user= new Client(username, passes, 4242);
        InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
        reader = new BufferedReader(streamReader);
        writer = new PrintWriter(sock.getOutputStream());
        System.out.println("Group network established");
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

