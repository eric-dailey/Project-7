/*
 *	Project 7 ChatRoom
 *	Eric Dailey <emd925>
 *	Shariq Memon <skm2662>
 *	4/29/2017
 *	Slip day used: Yes
 */

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
    boolean loggedin;

    public Client(String i , String Pass, int p) throws IOException {
        id = i;
        password = Pass;
        loggedin = false;
        port  = p;
        Chat.sock = new Socket("10.146.35.234", p);
    }
    public String getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }


}
