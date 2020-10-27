package Chat;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        String hostName = "localhost";
        int portNumber = 2048;

        try{
            socket = new Socket(hostName, portNumber);
            ThreadGetSend tgs = new ThreadGetSend(socket);
            tgs.start();
            ThreadRecievePrint trp = new ThreadRecievePrint(socket);
            trp.start();
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
