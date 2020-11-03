import java.io.*;
import java.net.*;

public class ThreadRecievePrint extends Thread{
    private Socket socket = null;
    BufferedReader in = null;

    public ThreadRecievePrint(Socket socket) {
        super("ThreadRecievePrint");
        this.socket = socket;
    }

    public void run() {
        super.run();

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fromServer = null;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Message: " + fromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
