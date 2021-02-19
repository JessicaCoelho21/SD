import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static SynchronizedList userList = new SynchronizedList();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int port  = 2048;
        boolean listening = true;

        User user = new User(1, "Jessica", "912345678");
        userList.addUser(user);

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (listening){
            ThreadSocket threadSocket = new ThreadSocket(serverSocket.accept());
            threadSocket.start();
        }

        serverSocket.close();
    }
}
