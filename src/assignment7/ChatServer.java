package assignment7;

/**
 * Created by home on 4/26/17.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private ArrayList<PrintWriter> clientOutputStreams;
    private ArrayList<PrintWriter> groupStreams;

    public static void main(String[] args) {
        try {
            new ChatServer().setUpNetworking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpNetworking() throws Exception {
        clientOutputStreams = new ArrayList<PrintWriter>();
        groupStreams = new ArrayList<PrintWriter>();
        @SuppressWarnings("resource")
        ServerSocket serverSock = new ServerSocket(4242);
        ServerSocket groupsock = new ServerSocket(5655);

        while (true) {
            Socket clientSocket = serverSock.accept();
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
            clientOutputStreams.add(writer);

            Thread t = new Thread(new ClientHandler(clientSocket));
            t.start();
            System.out.println("got a connection");

            Socket groupSocket = groupsock.accept();
            PrintWriter groupwriter = new PrintWriter(groupSocket.getOutputStream());
            groupStreams.add(groupwriter);

            Thread x = new Thread(new ClientHandler(groupSocket));
            x.start();
            System.out.println("group got a a connection");
        }



    }

    private void notifyClients(String message) {


        for (PrintWriter writer : clientOutputStreams) {
            writer.println(message);
            writer.flush();
        }

        for(PrintWriter groupwriter : groupStreams){
            {
                groupwriter.println(message);
                groupwriter.flush();
            }
        }
    }

    class ClientHandler implements Runnable {
        private BufferedReader reader;

        public ClientHandler(Socket clientSocket) throws IOException {
            Socket sock = clientSocket;
            reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    notifyClients(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
