package assignment7.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Created by home on 4/27/17.
 */
public class Client {

    String id;
    String password;
    int port;

    public Client(String i , String Pass, int p) throws IOException {
        id = i;
        password = Pass;
        port  = p;
        Chat.sock = new Socket("127.0.0.1", p);
    }


}
