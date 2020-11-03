package MultiEcho;

import java.net.*;
import java.io.*;

public class EchoServerThread extends Thread{
    private Socket socket = null;

    public EchoServerThread(Socket socket) {
        super("EchoServerThread");
        this.socket = socket;
    }

    public void run(){
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }

            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + socket.getLocalPort() + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
