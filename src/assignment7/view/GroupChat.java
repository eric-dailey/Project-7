package assignment7.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import assignment7.ChatServer;
import assignment7.GroupChatServer;
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
        GroupChatServer.groupusers.add(GroupChatServer.groupuserList.get(Integer.valueOf(ID)));
        GroupsetUpNetworking();

    }



    private void GroupsetUpNetworking() throws Exception {

        groupsock = new Socket("10.146.210.237" , 5655);
        InputStreamReader groupstream = new InputStreamReader(groupsock.getInputStream());
        groupreader = new BufferedReader(groupstream);
        groupwriter = new PrintWriter(groupsock.getOutputStream());
        System.out.println("Group network established");
        Thread groupThread = new Thread(new GroupIncomingReader());
        groupThread.start();
    }
    public void groupsend(){

        groupwriter.println(groupoutput.getText());
        groupwriter.flush();
        groupoutput.setText("");
        groupoutput.requestFocus();
    }


    class GroupIncomingReader implements Runnable {
        public void run() {
            String messages;
            try {
                while ((messages = groupreader.readLine()) != null) {

                    groupinput.appendText(messages + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
