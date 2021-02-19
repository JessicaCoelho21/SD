import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadSocket extends Thread {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ThreadSocket(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run(){
        super.run();

        try{
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            User user;

            while ((user = (User) inputStream.readObject()) != null) {
                System.out.println("User: " + user.toString());
                ArrayList<User> userArrayList = Main.userList.getAllUsers();
                System.out.println("Saved!");
                for (User nUser : userArrayList) {
                    if (nUser.getId() == user.getId()) {
                        if (user.getPswd().equals(user.getPswd())) {
                            System.out.println("Authenticated!");
                        } else {
                            System.out.println("Wrong password!");
                        }
                    }
                }
            }

            inputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            inputStream.close();
            socket.close();
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
