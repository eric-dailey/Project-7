package assignment7.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;



/**
 * Created by home on 4/27/17.
 */
public class GroupChat {


    @FXML
    private TextArea output;


    @FXML
    private TextArea input;

    private BufferedReader reader;

    private PrintWriter writer;

    public static Socket sock;

    public Client user;
}
