import java.io.*;
import java.net.*;

public class ThreadGetSend extends Thread{
    private Socket socket = null;
    PrintWriter out = null;

    public ThreadGetSend(Socket socket) {
        super("ThreadGetSend");
        this.socket = socket;
    }

    public void run() {
        super.run();

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromUser;

            while((fromUser = stdIn.readLine()) != null){
                out.println(fromUser);
                System.out.println("Sent by me: " + fromUser);

                if(fromUser.equals("Bye")){
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
