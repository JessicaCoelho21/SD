import java.io.*;
import java.net.*;

public class Main {
    private static Socket socket = null;
    private static ObjectInputStream inputStream;
    private static ObjectOutputStream outputStream;
    private static final long SLEEP = 10000;

    public static void main(String[] args) {
        try {
            socket = new Socket("127.0.0.1", 2048);

            while (true){
                send();
                Thread.sleep(SLEEP);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void send() {
        if (socket != null) {
            try {
                outputStream = new ObjectOutputStream(socket.getOutputStream());

                User user = new User(1, "Jessica", "912345678");
                outputStream.writeObject(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error");
        }
    }
}
