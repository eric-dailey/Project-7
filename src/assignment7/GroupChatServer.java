/*
 *	Project 7 ChatRoom
 *	Eric Dailey <emd925>
 *	Shariq Memon <skm2662>
 *	4/29/2017
 *	Slip day used: Yes
 */

package assignment7;

/**
 * Created by home on 4/26/17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class GroupChatServer extends Observable{

        public static HashMap<Integer, String> groupuserList = new HashMap<Integer, String>();
        public static ArrayList<String> groupusers = new ArrayList<String>();
        private static int counter = 1;
        private ArrayList<Socket> trueList = new ArrayList<Socket>();
        private HashMap<Integer, Integer> PortToIDList = new HashMap<Integer, Integer>();

        public static void main(String[] args) {
            try {
                new GroupChatServer().setUpNetworkingGroup();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Integer getValue(int Integer) {
            System.out.println(PortToIDList.keySet());
            return PortToIDList.get(Integer);

        }

        private void setUpNetworkingGroup() throws Exception {
            @SuppressWarnings("resource")

            ServerSocket groupServerSock = new ServerSocket(5655);
            while (true) {
                Socket groupclientSocket = groupServerSock.accept();
                ClientObserver groupwriter = new ClientObserver(groupclientSocket.getOutputStream());
                Thread x = new Thread(new GroupClientHandler(groupclientSocket));
                x.start();
                this.addObserver(groupwriter);
                setChanged();
                notifyObservers("User " + counter + " has connected to chat.");
                PortToIDList.put(groupclientSocket.getPort(), counter);
                System.out.println(groupclientSocket);
                groupuserList.put(counter, "User " + counter);
                trueList.add(groupclientSocket);
                System.out.println("Connection. It's ID is " + counter + ".");
            }
        }

        class GroupClientHandler implements Runnable {
            private BufferedReader reader;
            private Socket holdme;

            public GroupClientHandler(Socket clientSocket) {
                Socket sock = clientSocket;
                holdme = clientSocket;
                try {
                    reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void run() {
                String message;
                try {
                    while ((message = reader.readLine()) != null) {
                        System.out.println(
                                "Message received from User " + PortToIDList.get(holdme.getPort()) + ". " + message);
                        if (message.equals("has disconnected.")) {
                            setChanged();
                            notifyObservers(
                                    "User " + PortToIDList.get(holdme.getPort()) + " has disconnected from group chat.");
                            PortToIDList.remove(holdme.getPort());
                            holdme.close();
                        } else if (message.contains("+")) {
                            String newMessage = message.substring(0, message.length() - 1);
                            int clientID = PortToIDList.get(Integer.parseInt(newMessage));
                            setChanged();
                            notifyObservers(message + clientID);
                        } else {
                            setChanged();
                            String name = groupuserList.get(PortToIDList.get(holdme.getPort()));
                            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")).toString();
                            notifyObservers(time + " " + name + ": " + message);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



